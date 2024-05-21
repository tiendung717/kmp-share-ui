package com.chipmango.kmp.uikit.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.chipmango.kmp.core.theme.typography.UIKitTypography
import com.chipmango.kmp.theme.themeColors
import org.jetbrains.compose.ui.tooling.preview.Preview

data class ContextMenuItem(
    val icon: ImageVector? = null,
    val title: String,
    val enabled: Boolean = true,
    val onClick: () -> Unit
)

@Composable
fun ContextMenu(
    modifier: Modifier,
    icon: ImageVector,
    iconTintColor: Color = themeColors().text.Stronger,
    containerColor: Color = themeColors().background.Strong,
    contextMenuItems: List<ContextMenuItem>,
    contentColor: Color = themeColors().text.Stronger
) {
    var isOpen by remember { mutableStateOf(false) }

    Box(modifier = modifier) {
        IconButton(onClick = { isOpen = !isOpen }) {
            Icon(
                imageVector = icon,
                contentDescription = "context_menu_icon",
                tint = iconTintColor
            )
        }

        DropdownMenu(
            modifier = Modifier
                .width(200.dp)
                .background(containerColor),
            expanded = isOpen,
            onDismissRequest = { isOpen = false }
        ) {
            contextMenuItems.forEachIndexed { index, contextMenuItem ->
                DropdownMenuItem(
                    onClick = {
                        if (contextMenuItem.enabled) {
                            contextMenuItem.onClick()
                            isOpen = false
                        }
                    },
                    contentPadding = PaddingValues(
                        horizontal = 12.dp,
                        vertical = 4.dp
                    ),
                    enabled = contextMenuItem.enabled,
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = contextMenuItem.title,
                            style = UIKitTypography.Body2Regular14,
                            color = contentColor
                        )
                        Spacer(Modifier.width(12.dp))
                        Icon(
                            modifier = Modifier.size(24.dp),
                            imageVector = icon,
                            contentDescription = null,
                            tint = contentColor
                        )
                    }
                }

                if (index < contextMenuItems.size - 1) {
                    Divider(
                        color = themeColors().divider.Normal
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewContextMenu() {
    ContextMenu(
        modifier = Modifier,
        icon = Icons.Rounded.Add,
        contextMenuItems = listOf(
            ContextMenuItem(
                icon = Icons.Rounded.Edit,
                title = "Edit",
                onClick = {}
            ),
            ContextMenuItem(
                icon = Icons.Rounded.Delete,
                title = "Delete",
                onClick = {}
            )
        )
    )
}