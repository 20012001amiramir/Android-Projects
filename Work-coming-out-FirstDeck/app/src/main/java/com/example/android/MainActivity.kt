package com.example.android

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var adapter: Adapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter = Adapter(getDataSource()) { name, desc, img ->
            navigateToDescription(name, desc, img)
        }
        val layoutManager = GridLayoutManager(this, 2)
        rv_cities!!.setLayoutManager(layoutManager)
        rv_cities!!.addItemDecoration(SpaceGrid(3, 10, true))

        rv_cities.adapter = adapter
    }
    private inner class SpaceGrid(private val mSpanCount: Int, private val mSpacing: Int, private val mIncludeEdge: Boolean) : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
            val position = parent.getChildAdapterPosition(view)
            val column = position % mSpanCount
            if (mIncludeEdge) {
                outRect.left = mSpacing - column * mSpacing / mSpanCount
                outRect.right = (column + 1) * mSpacing / mSpanCount
                if (position < mSpanCount) {
                    outRect.top = mSpacing
                }
                outRect.bottom = mSpacing
            } else {
                outRect.left = column * mSpacing / mSpanCount
                outRect.right = mSpacing - (column + 1) * mSpacing / mSpanCount
                if (position < mSpanCount) {
                    outRect.top = mSpacing
                }
            }
        }
    }
    private fun navigateToDescription(name: String, desc: String, img: Int) {
        startActivity(DescActivity.createIntent(this, name, desc, img))
    }

    private fun getDataSource(): List<Sport> = Data.objects.toList()
}
