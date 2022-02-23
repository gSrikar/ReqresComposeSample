package com.gsrikar.reqressample.users

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.gsrikar.reqressample.R
import com.gsrikar.reqressample.models.ReqresData
import com.gsrikar.reqressample.ui.theme.ReqresSampleTheme


/**
 * Each card item
 */
@Composable
fun UserCardListItem(data: ReqresData) {
    Card(
        modifier = Modifier
            .padding(8.dp, vertical = 8.dp)
            .clickable(enabled = true) {},
        elevation = 4.dp,
        backgroundColor = Color.White,
        shape = RoundedCornerShape(corner = CornerSize(16.dp))
    ) {
        Row {
            // Show user profile image
            UserImage(
                Modifier
                    .padding(8.dp)
                    .size(84.dp)
                    .clip(RoundedCornerShape(corner = CornerSize(16.dp)))
                    .align(Alignment.CenterVertically), data.avatar
            )

            // Show user details
            UserDetail(
                Modifier
                    .padding(16.dp)
                    .weight(1f)
                    .align(Alignment.CenterVertically),
                data.first_name,
                data.last_name,
                data.email
            )

            // TODO: Store this state in database
            // TODO: Perform the migrations so that existing data doesn't get lost
            // TODO: Use the favorite information stored in the database
            var favorite = false


            // Create favorite image states
            val favoriteImageState = remember {
                mutableStateOf(
                    if (favorite) {
                        R.drawable.ic_favorite_solid
                    } else {
                        R.drawable.ic_favorite_border
                    }
                )
            }

            // Favorite user
            FavoriteUser(
                Modifier
                    .padding(8.dp)
                    .size(32.dp)
                    .clip(RoundedCornerShape(corner = CornerSize(16.dp)))
                    .align(Alignment.CenterVertically)
                    .clickable(
                        true,
                        onClick = {
                            // Update favorite state and favorite variable
                            favorite = favoriteClicked(favoriteImageState, favorite)
                        }),
                favoriteImageState
            )
        }
    }
}

/**
 * Update Favorite image id state and return the
 */
fun favoriteClicked(favoriteImageState: MutableState<Int>, favorite: Boolean): Boolean {
    return if (favorite) {
        // User doesn't want to mark the contacts as favorite
        // Reset the favorite state
        favoriteImageState.value = R.drawable.ic_favorite_border
        // Return the updated favorite information
        false
    } else {
        // User wants to mark the contacts as favorite
        // Set the favorite state
        favoriteImageState.value = R.drawable.ic_favorite_solid
        // Return the updated favorite information
        true
    }
}

/**
 * User profile image
 */
@OptIn(ExperimentalCoilApi::class)
@Composable
fun UserImage(modifier: Modifier, url: String) {
    Image(
        painter = rememberImagePainter(url),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
    )
}


/**
 * Show user details like firstname, lastname and email address
 */
@Composable
fun UserDetail(modifier: Modifier, firstName: String, lastName: String, email: String) {
    Column(
        modifier = modifier
    ) {
        // User first and last name
        Text(
            text = "$firstName $lastName", style = MaterialTheme.typography.h5
        )
        // User email address
        Text(text = email, style = MaterialTheme.typography.body1)
    }
}

/**
 * Shows whether the user is favorite or not
 */
@Composable
fun FavoriteUser(modifier: Modifier, favoriteImageState: MutableState<Int>) {
    Image(
        painter = painterResource(id = favoriteImageState.value),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
    )
}


/**
 * Preview for the card item
 */
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ReqresSampleTheme {
        UserCardListItem(
            ReqresData(
                1,
                "george.bluth@reqres.in",
                "George",
                "Bluth",
                "https://reqres.in/img/faces/1-image.jpg"
            )
        )
    }
}