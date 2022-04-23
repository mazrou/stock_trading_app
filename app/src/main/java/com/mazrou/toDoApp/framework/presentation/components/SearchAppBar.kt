package com.mazrou.toDoApp.framework.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp


@ExperimentalComposeUiApi
@Composable
fun SearchAppBar(
    query: String,
    onQueryChanged: (String) -> Unit,
    onExecuteSearch: () -> Unit,
) {

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        color = MaterialTheme.colors.background,
        elevation = 8.dp,
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                OutlinedTextField(
                    value = query,
                    onValueChange = {
                        onQueryChanged(it)
                    },
                    placeholder = {
                        Text(text = "ticker ...")
                    },
                    keyboardActions = KeyboardActions(onDone = {
                        onExecuteSearch()
                        keyboardController?.hide()
                        focusManager.clearFocus()
                    }),
                    shape = RoundedCornerShape(30.dp),
                    maxLines = 1,
                    modifier = Modifier
                        .fillMaxWidth(1.4f)
                        .padding(10.dp),

                    label = {
                        Text(text = "Search")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done,

                        ),
                    textStyle = TextStyle(color = MaterialTheme.colors.onSurface),
                    leadingIcon = {
                        Icon(Icons.Filled.Search, contentDescription = "Search Icon")
                    },
                    trailingIcon = {
                        Icon(
                            Icons.Filled.Clear,
                            contentDescription = "Clear icon",
                            modifier = Modifier
                                .clickable {
                                    onQueryChanged("")
                                    onExecuteSearch()
                                    keyboardController?.hide()
                                    focusManager.clearFocus()
                                }
                        )
                    },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(0xFF929191),
                        focusedLabelColor = Color(0xFF929191)
                    )
                )
            }
        }
    }
}