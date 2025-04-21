package com.music_app.ui.components

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.music_app.ui.theme.LocalCustomColors
import com.music_app.ui.theme.LocalCustomTypography

@Composable
fun CustomSearchBar(
    text: MutableState<String>,
    @StringRes hintResId: Int = 0,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onSearch: (String) -> Unit,
    onClear: () -> Unit
) {
    val customsColors = LocalCustomColors.current
    val customTypography = LocalCustomTypography.current
    val isSearchActive = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .background(customsColors.bottomNavItem)
            .padding(bottom = 6.dp)
            .fillMaxWidth()
            .background(customsColors.backgroundItem, RoundedCornerShape(8.dp))
            .padding(10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            BasicTextField(
                value = text.value,
                onValueChange = { newValue ->
                    text.value = newValue
                    if (newValue.isEmpty()) {
                        isSearchActive.value = false
                    }
                    onClear()
                },
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                enabled = true,
                keyboardOptions = keyboardOptions,
                textStyle = customTypography.bigDescription.copy(
                    fontWeight = FontWeight.Medium
                ),
                decorationBox = { innerTextField ->
                    if (text.value.isEmpty()) {
                        Text(
                            text = stringResource(id = hintResId),
                            style = customTypography.bigDescription.copy(
                                fontWeight = FontWeight.Medium
                            )
                        )
                    }
                    innerTextField()
                }
            )

            Spacer(modifier = Modifier.width(8.dp))

            IconButton(
                onClick = {
                    if (isSearchActive.value) {
                        text.value = ""
                        isSearchActive.value = false
                        onClear()
                    } else {
                        onSearch(text.value)
                        isSearchActive.value = true
                    }
                }
            ) {
                Icon(
                    imageVector = if (isSearchActive.value) Icons.Default.Close else Icons.Default.Search,
                    contentDescription = if (isSearchActive.value) "Clear" else "Search"
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewCustomSearchBar() {
    val textState: MutableState<String> = remember { mutableStateOf("Поиск треков...") }

    CustomSearchBar(
        text = textState,
        onSearch = {},
        onClear = {}
    )
}