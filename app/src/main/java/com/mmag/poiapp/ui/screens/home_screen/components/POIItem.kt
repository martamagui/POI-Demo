package com.mmag.poiapp.ui.screens.home_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mmag.poiapp.R
import com.mmag.poiapp.data.model.POIDetail


@Composable
fun POIiItem(poi: POIDetail, onclick: () -> Unit, modifier: Modifier) {
    ElevatedCard(
        onClick = { onclick() },
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(poi.image)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.ic_photo_place_holder),
                contentDescription = poi.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(92.dp)
                    .clip(RoundedCornerShape(12.dp))
            )
            Column {
                Text(text = poi.title, style = MaterialTheme.typography.titleMedium)
                Text(text = stringResource(id = R.string.home_location_text, poi.geocoordinates))
            }
        }

    }
}