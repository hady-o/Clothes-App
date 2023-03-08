package com.example.elpop

import android.graphics.Bitmap
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.elpop.adapters.*
import com.example.elpop.data.*


//@BindingAdapter("listData")
//fun bindRecyclerView(recyclerView: RecyclerView, data: List<PostClass>) {
//    val adapter = recyclerView.adapter as PsAdapter
//     adapter.submitList(data)
//}
//
//@BindingAdapter("listDisease")
//fun bindDisease(recyclerView: RecyclerView, data: List<DiseaseClass>) {
//    val adapter = recyclerView.adapter as DiseaseAdapter
//    adapter.submitList(data)
//}
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Clothe>) {
  val adapter = recyclerView.adapter as ClothAdapter
  adapter.submitList(data)
}
@BindingAdapter("reportListData")
fun reportBindRecyclerView(recyclerView: RecyclerView, data: List<Report>) {
  val adapter = recyclerView.adapter as ReportAdapter
  adapter.submitList(data)
}

@BindingAdapter("personListData")
fun personBindRecyclerView(recyclerView: RecyclerView, data: List<Person>) {
  val adapter = recyclerView.adapter as PersonAdapter
  adapter.submitList(data)
}

@BindingAdapter("cartListData")
fun cartBindRecyclerView(recyclerView: RecyclerView, data: List<Cart>) {
  val adapter = recyclerView.adapter as CartAdapter
  adapter.submitList(data)
}
@BindingAdapter("historyListData")
fun historyBindRecyclerView(recyclerView: RecyclerView, data: List<History>) {
  val adapter = recyclerView.adapter as HistoryAdapter
  adapter.submitList(data)
}

@BindingAdapter("historyItemListData")
fun historyItemBindRecyclerView(recyclerView: RecyclerView, data: List<HistoryItem>) {
  val adapter = recyclerView.adapter as HistoryItemAdapter
  adapter.submitList(data)
}


@BindingAdapter("loadImage")
fun loadImage(imgView: ImageView,type:String) {
  if (type == "cloth") {
    imgView.setImageResource(R.drawable.icy_clothes_svgrepo_com)
  } else if (type == "foot") {
    imgView.setImageResource(R.drawable.icy_footwear_shoes_svgrepo_com)
  } else {
    imgView.setImageResource(R.drawable.icy_accessories_apparel_cap_svgrepo_com)
  }
}
  @BindingAdapter("reportLoadImage")
  fun reportLoadImage(imgView: ImageView,type:String) {
    if (type == "im") {
      imgView.setImageResource(R.drawable.ic_baseline_arrow_downward_24)
    } else {
      imgView.setImageResource(R.drawable.ic_baseline_arrow_upward_24)
    }
  }

