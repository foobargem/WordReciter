package org.inkwang.two.widget;

import org.inkwang.two.WordListActivity;

import android.R;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class WordWidgetProvider extends AppWidgetProvider {
	
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		final int idsLength = appWidgetIds.length;
		
		for (int i = 0; i < idsLength; i++) {
			int appWidgetId = appWidgetIds[i];
			
			Intent intent = new Intent(context, WordListActivity.class);
			PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
			
			//RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.);
			//views.setOnClickPendingIntent(R.id.button, views)
			
		}
	}

}
