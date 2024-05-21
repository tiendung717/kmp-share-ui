package com.chipmango.kmp.uikit.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.chipmango.kmp.core.theme.typography.UIKitTypography
import com.chipmango.kmp.theme.themeColors
import com.chipmango.kmp.uikit.button.AppTextButton
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun <T> DialogSingleSelectionList(
    title: String,
    description: String? = null,
    items: List<T>,
    selectedItem: T?,
    onDismissRequest: () -> Unit,
    onItemSelected: (T) -> Unit,
    icon: @Composable ((T) -> Unit)? = null,
    text: @Composable (T) -> Unit,
    negativeButtonText: String? = null,
    positiveButtonText: String,
    containerColor: Color = themeColors().background.Normal,
    shape: Shape = RoundedCornerShape(8.dp)
) {
    var currentSelectedItem by remember(selectedItem) {
        mutableStateOf(selectedItem)
    }

    Dialog(onDismissRequest = onDismissRequest) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = containerColor, shape = shape)
                .clip(shape)
                .padding(
                    horizontal = 16.dp,
                    vertical = 16.dp
                ),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = title,
                style = UIKitTypography.Body1Medium16,
                color = themeColors().text.Stronger
            )

            description?.let {
                Text(
                    text = it,
                    style = UIKitTypography.Body2Regular14,
                    color = themeColors().text.Normal
                )
            }

            Divider(color = themeColors().divider.Normal)

            items.forEach {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (icon != null) {
                        icon(it)
                    }
                    text(it)
                    Spacer(modifier = Modifier.weight(1f))
                    Checkbox(
                        checked = it == currentSelectedItem,
                        onCheckedChange = { checked ->
                            if (checked) {
                                currentSelectedItem = it
                            }
                        },
                        colors = CheckboxDefaults.colors(
                            checkmarkColor = themeColors().textInvert.Stronger,
                            checkedColor = themeColors().project.Normal,
                            uncheckedColor = themeColors().project.Normal
                        )
                    )
                }
            }

            Divider(color = themeColors().divider.Normal)

            Row(
                modifier = Modifier.align(Alignment.End),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                negativeButtonText?.let {
                    AppTextButton(
                        onClick = onDismissRequest,
                        text = it
                    )
                }

                AppTextButton(
                    onClick = {
                        currentSelectedItem?.let { onItemSelected(it) }
                    },
                    text = positiveButtonText
                )
            }
        }
    }
}


@Preview
@Composable
fun DialogSingleSelectionListPreview() {
    DialogSingleSelectionList(
        title = "Select an item",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed non risus. Suspendisse lectus tortor, dignissim sit amet, adipiscing nec, ultricies sed, dolor. Cras elementum ultrices diam..",
        items = listOf("Item 1", "Item 2", "Item 3"),
        selectedItem = "Item 2",
        onDismissRequest = {},
        onItemSelected = {},
        icon = {
            Icon(imageVector = Icons.Rounded.Add, contentDescription = null)
        },
        text = {
            Text(text = it)
        },
        negativeButtonText = "Cancel",
        positiveButtonText = "Select"
    )
}