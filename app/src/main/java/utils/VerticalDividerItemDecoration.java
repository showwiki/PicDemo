package utils;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ItemDecoration;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;
import androidx.recyclerview.widget.RecyclerView.LayoutParams;
import androidx.recyclerview.widget.RecyclerView.State;

/**
 * Created by 逢朋 on 2017/9/23.
 */
public class VerticalDividerItemDecoration extends ItemDecoration {
    private int divider;
    private Context mContext;
    private int dp1;

    public VerticalDividerItemDecoration(Context mContext) {
        this.mContext = mContext;
        dp1 = (int) (0.5F + mContext.getResources().getDisplayMetrics().density);
        divider = dp1 * 15;
    }

    public VerticalDividerItemDecoration(Context mContext, int dividerWidth) {
        this.mContext = mContext;
        dp1 = (int)(0.5F + mContext.getResources().getDisplayMetrics().density);
        divider = dp1 * dividerWidth;
    }

    private boolean isLastColumn(RecyclerView recyclerView, int i) {
        LayoutManager layoutManager = recyclerView.getLayoutManager();
        return layoutManager instanceof GridLayoutManager ? isTheColumn(recyclerView, i, ((GridLayoutManager) layoutManager).getSpanCount() - 1) : false;
    }

    private boolean isTheColumn(RecyclerView recyclerView, int postion, int count) {
        LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            int spanCount = gridLayoutManager.getSpanCount();
            int spanIndex = gridLayoutManager.getSpanSizeLookup().getSpanIndex(postion, spanCount);
            if (gridLayoutManager.getSpanSizeLookup().getSpanSize(postion) != spanCount && spanIndex == count) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, State state) {
        int viewLayoutPosition = ((LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
        if (isLastColumn(recyclerView, viewLayoutPosition)) {
            rect.set(divider / 4, 0, divider, divider);
        } else if (isTheColumn(recyclerView, viewLayoutPosition, 0)) {
            rect.set(divider, 0, divider / 4, divider);
        } else if (isTheColumn(recyclerView, viewLayoutPosition, 1)) {
            rect.set((divider * 3) / 4, 0, divider / 2, divider);
        } else if (isTheColumn(recyclerView, viewLayoutPosition, 2)) {
            rect.set(divider / 2, 0, (divider * 3) / 4, divider);
        }
    }
}
