package com.chipmango.kmp.uikit.topbar

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.List
import androidx.compose.material.icons.automirrored.rounded.Send
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Done
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.chipmango.kmp.core.theme.typography.UIKitTypography
import com.chipmango.kmp.theme.themeColors
import com.chipmango.kmp.uikit.menu.ContextMenu
import com.chipmango.kmp.uikit.menu.ContextMenuItem
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun AppTopBar(
    title: String,
    actionIcon: ImageVector? = null,
    navigationIcon: ImageVector? = Icons.AutoMirrored.Rounded.List,
    containerColor: Color = themeColors().background.Normal,
    iconTintColor: Color = themeColors().text.Stronger,
    contentColor: Color = themeColors().text.Stronger,
    onNavigationClick: () -> Unit = {},
    onActionClick: () -> Unit = {}
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                style = UIKitTypography.Title3SemiBold18
            )
        },
        navigationIcon = navigationIcon?.let {
            {
                IconButton(onClick = onNavigationClick) {
                    Icon(
                        imageVector = it,
                        contentDescription = "Back",
                        tint = iconTintColor
                    )
                }
            }
        } ?: {},
        actions = actionIcon?.let {
            {
                IconButton(onClick = onActionClick) {
                    Icon(it, contentDescription = "Menu")
                }
            }
        } ?: {},
        backgroundColor = containerColor,
        contentColor = contentColor
    )
}

@Composable
fun AppTopBarWithMenu(
    title: String,
    actionIcon: ImageVector,
    navigationIcon: ImageVector? = Icons.AutoMirrored.Rounded.Send,
    onNavigationClick: () -> Unit = {},
    contextMenuItems: List<ContextMenuItem>,
    contextMenuContainerColor: Color = themeColors().background.Normal,
    contextMenuContentColor: Color = themeColors().text.Stronger
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                style = UIKitTypography.Title3SemiBold18
            )
        },
        navigationIcon = navigationIcon?.let {
            {
                IconButton(onClick = onNavigationClick) {
                    Icon(it, contentDescription = "Back")
                }
            }
        } ?: {},
        actions = {
            ContextMenu(
                modifier = Modifier,
                icon = actionIcon,
                contextMenuItems = contextMenuItems,
                containerColor = contextMenuContainerColor,
                contentColor = contextMenuContentColor,
                iconTintColor = contextMenuContentColor
            )
        },
        backgroundColor = themeColors().background.Normal,
        contentColor = themeColors().text.Stronger,
    )
}

@Preview
@Composable
fun AppTopBarPreview() {
    AppTopBar(
        title = "Title",
        actionIcon = Icons.Rounded.Done,
        navigationIcon = Icons.Rounded.Clear
    )
}
