package com.example.myapplication2.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import com.example.myapplication2.entity.SubscriptionLevel
import com.example.myapplication2.entity.User
import com.example.myapplication2.viewmodel.AppViewModel

@Composable
fun MyApplicationScreen(viewModel: AppViewModel) {
    val users by viewModel.allUsers.observeAsState(emptyList())
    val subscriptionLevels by viewModel.allSubscriptionLevels.observeAsState(emptyList())
    var showAddDialog by remember { mutableStateOf(false) }
    var editUser by remember { mutableStateOf<User?>(null) }

    Column(modifier = Modifier.padding(25.dp)) {
        Button(onClick = { showAddDialog = true }) {
            Text("Додати користувача")
        }
        Spacer(modifier = Modifier.height(20.dp))

        users.forEach { user ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text("${user.userNickname} \n(${user.subscriptionLevelName})")
                        Text("Днів підписки: ${user.daysOfSubscription}")
                    }
                    Row {
                        Button(onClick = { editUser = User(user.userId, user.userNickname, user.daysOfSubscription, user.subscriptionLevelId) }) {
                            Text("Змінити")
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(onClick = {
                            viewModel.allUsers.value?.firstOrNull { it.userId == user.userId }?.let {
                                viewModel.delete(User(it.userId, it.userNickname, it.daysOfSubscription, it.subscriptionLevelId))
                            }
                        }) {
                            Text("Видалити")
                        }
                    }
                }
            }
        }
    }

    if (showAddDialog && subscriptionLevels.isNotEmpty()) {
        UserDialog(
            subscriptionLevels = subscriptionLevels,
            onDismiss = { showAddDialog = false },
            onSave = { nickname, daysOfSubscription, subscriptionLevelId ->
                viewModel.insert(User(0, nickname, daysOfSubscription.toInt(), subscriptionLevelId.toLong()))
                showAddDialog = false
            }
        )
    }

    editUser?.let { user ->
        UserDialog(
            initialUser = user,
            subscriptionLevels = subscriptionLevels,
            onDismiss = {editUser = null},
            onSave = { nickname, daysOfSubscription, subscriptionLevelId ->
                viewModel.update(User(user.id, nickname, daysOfSubscription.toInt(), subscriptionLevelId.toLong()))
                editUser = null
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDialog(
    initialUser: User? = null,
    subscriptionLevels: List<SubscriptionLevel>,
    onDismiss: () -> Unit,
    onSave: (nickname: String, daysOfSubscription: String, subscriptionLevelId: Int) -> Unit
) {
    var nickname by remember { mutableStateOf(initialUser?.nickname ?: "") }
    var daysOfSubscription by remember { mutableStateOf(initialUser?.daysOfSubscription?.toString() ?: "") }
    var selectedSubscriptionLevel by remember(subscriptionLevels) {
        mutableStateOf(
            initialUser?.let { user -> subscriptionLevels.find { it.id == user.subscriptionLevelId } }
                ?: subscriptionLevels.firstOrNull()
        )
    }
    var expanded by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(if (initialUser == null) "Додати користувача" else "Змінити користувача") },
        text = {
            Column {
                OutlinedTextField(
                    value = nickname,
                    onValueChange = { nickname = it },
                    label = { Text("Нікнейм") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = daysOfSubscription,
                    onValueChange = { daysOfSubscription = it },
                    label = { Text("Днів підписки") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded }
                ) {
                    OutlinedTextField(
                        value = selectedSubscriptionLevel?.name ?: "",
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Підписка") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                        modifier = Modifier.menuAnchor()
                    )
                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        subscriptionLevels.forEach { subscriptionLevel ->
                            DropdownMenuItem(
                                text = { Text(subscriptionLevel.name) },
                                onClick = {
                                    selectedSubscriptionLevel = subscriptionLevel
                                    expanded = false
                                }
                            )
                        }
                    }
                }
            }
        },
        confirmButton = {
            Button(
                onClick =
                    {
                        if (nickname.isNotBlank() && daysOfSubscription.isNotBlank()) {
                            selectedSubscriptionLevel?.let { subscriptionLevel ->
                                onSave(nickname, daysOfSubscription, subscriptionLevel.id.toInt()) }
                        }
                    }
            ) { Text("Зберегти") }
        },
        dismissButton = {
            Button(onClick = onDismiss) { Text("Скасувати") }
        }
    )
}
