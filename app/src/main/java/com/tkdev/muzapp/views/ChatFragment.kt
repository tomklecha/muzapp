package com.tkdev.muzapp.views

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.tkdev.muzapp.R
import com.tkdev.muzapp.adapters.MessageListAdapter
import com.tkdev.muzapp.databinding.FragmentChatBinding
import com.tkdev.muzapp.domain.models.mocks.MockUsers
import com.tkdev.muzapp.viewmodels.MuzViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatFragment : Fragment() {

    private val muzViewModel: MuzViewModel by activityViewModels()
    private val args: ChatFragmentArgs by navArgs()

    private lateinit var sendButton: MaterialButton
    private lateinit var chatTextField: TextInputEditText
    private lateinit var messageList: RecyclerView
    private lateinit var linearLayoutManager: LinearLayoutManager

    private lateinit var toolbar: MaterialToolbar
    private lateinit var binding: FragmentChatBinding
    private lateinit var chatAdapter: MessageListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChatBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
        }

        toolbar = binding.root.findViewById(R.id.toolbar)
        toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_insert_message -> {
                    muzViewModel.insertMockMessage(args.chatId)
                    true
                }
                R.id.menu_populate_db -> {
                    muzViewModel.prepopulateData()
                    true
                }
                R.id.menu_delete_db -> {
                    muzViewModel.clearChat()
                    true
                }
                else -> false
            }
        }
        toolbar.setNavigationOnClickListener {
            muzViewModel.showSnackbar(getString(R.string.action_navigation_back))
        }

        linearLayoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        chatAdapter = MessageListAdapter(MockUsers.currentUser)

        messageList = binding.root.findViewById(R.id.chat_recycler_view)
        messageList.apply {
            layoutManager = linearLayoutManager
            adapter = chatAdapter
        }

        chatTextField = binding.root.findViewById(R.id.chat_edit_text)

        sendButton = binding.root.findViewById(R.id.chat_send_button)

        sendButton.setOnClickListener {
            muzViewModel.sendChatMessage(chatTextField.text.toString(), args.chatId)
        }

        chatTextField.apply {
            addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    when (s.isNullOrEmpty()) {
                        true -> {
                            sendButton.isEnabled = false
                        }
                        false -> {
                            sendButton.isEnabled = true
                        }
                    }
                }

                override fun afterTextChanged(s: Editable?) {}

            })
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        muzViewModel.fetchChatMessages(args.chatId)

        muzViewModel.secondUserDomain.observe(viewLifecycleOwner, { user ->
            binding.apply {
                recipient = user
            }
        })

        muzViewModel.chatItems.observe(viewLifecycleOwner, { list ->
            chatAdapter.submitList(list)
        })

        muzViewModel.snackbarMessage.observe(viewLifecycleOwner, { event ->
            if (event.getContentIfNotHandled() != null) {
                val snackbar =
                    Snackbar.make(binding.root, event.peekContent(), Snackbar.LENGTH_SHORT)
                snackbar.anchorView = chatTextField
                snackbar.show()
            }
        })

        muzViewModel.editTextValue.observe(viewLifecycleOwner, { event ->
            if (event.getContentIfNotHandled() != null) {
                chatTextField.setText(event.peekContent())
            }
        })
    }
}