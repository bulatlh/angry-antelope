package com.me.mygdxgame;

import android.os.Bundle;
import android.app.Activity;
import android.app.Application;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

// import the Amazon GameCircle API into your game
import java.util.EnumSet;

import com.amazon.ags.api.AGResponseCallback;
import com.amazon.ags.api.AGResponseHandle;
import com.amazon.ags.api.AmazonGames;
import com.amazon.ags.api.AmazonGamesCallback;
import com.amazon.ags.api.AmazonGamesClient;
import com.amazon.ags.api.AmazonGamesFeature;
import com.amazon.ags.api.AmazonGamesStatus;
import com.amazon.ags.api.leaderboards.*;
import com.amazon.ags.api.*;

public class GameServices {
	private String defaultLeaderboard = "test_leaderbard";
	
	public static GameServices gameServices = null;
	
	// We'll initialize this once the application has launched
	AmazonGames agsGameClient;
	
	// Make a list of the Amazon GameCircle features your game uses.
	// TODO The case for this is incorrect on the GameCircle setup
	EnumSet<AmazonGamesFeature> agsGameFeatures = EnumSet.of(
			AmazonGamesFeature.Achievements,
			AmazonGamesFeature.Whispersync,
			AmazonGamesFeature.Leaderboards
	);
	
	AmazonGamesCallback agsGameCallback = new AmazonGamesCallback() {
		@Override
		public void onServiceReady() {
		}
		
		@Override
		public void onServiceNotReady(AmazonGamesStatus reason) {
			switch (reason) {
			case CANNOT_AUTHORIZE:
				/**
				 * The service could not authorize the client. This should only
				 * occur is the network is not available the first time the game
				 * attempts to connect.
				 */
			case CANNOT_BIND:
				/**
				 * The service could not bind either because it does not exist,
				 * or permissions have not been granted. This will also occur
				 * when your game is executed on a non-fire device that does not
				 * have Amazon GameCircle installed.
				 */
			case NOT_AUTHENTICATED:
				/**
				 * The device is not registered with an account.
				 * Disable Amazon GameCircle features in Game UI.
				 */
			case NOT_AUTHORIZED:
				/**
				 * The game is not authorized to use the service. Check your
				 * package name and signature registered in the Developer's
				 * Portal.
				 */
			case SERVICE_NOT_OPTED_IN:
				/**
				 * The device is not opted-in to use the service.
				 */
				break;
			default:
				break;
			}
			
		}
	};
	
	public GameServices(Application application) {
		agsGameClient = AmazonGamesClient.initialize(
				application,
				agsGameCallback,
				agsGameFeatures
		);
		
	}
	
	public AmazonGames getGameClient() {
		return agsGameClient;
	}
	
	public LeaderboardsClient getLeaderboardsClient() {
		return getGameClient().getLeaderboardsClient();
	}
	
	public void submitScore(long score) {
		submitScore(defaultLeaderboard, score);
	}
	
	public void showLeaderboardOverlay(String leaderboard) {
		getGameClient().getLeaderboardsClient().showLeaderboardOverlay(leaderboard);
	}
	
	public void showLeaderboardOverlay() {
		showLeaderboardOverlay(defaultLeaderboard);
	}
	
	public void submitScore(String leaderBoard, long score) {
		LeaderboardsClient lbClient = getLeaderboardsClient();
		AGResponseHandle<SubmitScoreResponse> handle = lbClient.submitScore(leaderBoard, score);
		
		// Optional callback to receive notification of success/failure.
		handle.setCallback(new AGResponseCallback<SubmitScoreResponse>() {
			@Override
			public void onComplete(SubmitScoreResponse result) {
				if (result.isError()) {
					// Score not sent
				} else {
					// Score sent
				}
			}
		});
	}
}
