package com.momolearn.model.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.momolearn.exception.MessageException;

@Service
public class KakaoService {
	
	public String getAccessToken(String code) {
		
		String accessToken = "";
		String refreshToken = "";
		String reqURL = "https://kauth.kakao.com/oauth/token";
		
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			conn.setDoInput(true);
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			StringBuilder sb = new StringBuilder();
			sb.append("grant_type=authorization_code");
			sb.append("&client_id=b05da66ce6b812c049b788547193fbdc");
			sb.append("&redirect_uri=http://localhost/momolearn/member/kakaoLogin");
			sb.append("&code="+code);
			
			bw.write(sb.toString());
			bw.flush();
			
			int responseCode = conn.getResponseCode();
			System.out.println("responseCode : " + responseCode); 

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String line = "";
			String result = "";
			
			while((line = br.readLine()) != null) {
				
				result += line;
			}
			
			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(result);
			
			accessToken  = element.getAsJsonObject().get("access_token").getAsString();
			refreshToken  = element.getAsJsonObject().get("refresh_token").getAsString();

			br.close();
			bw.close();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return accessToken;
	}

	public HashMap<String, Object> getUserInfo(String accessToken) {
		
		HashMap<String, Object> userInfo = new HashMap<String, Object>();
		String reqUrl = "https://kapi.kakao.com/v2/user/me";
		
		try {
			URL url = new URL(reqUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setRequestProperty("Authorization", "Bearer " + accessToken);
			
			int responseCode = conn.getResponseCode();
			
			if (responseCode != 200) {
				
				throw new MessageException("로그인에 실패하셨습니다.");
			}
			
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			String line = "";
			String result = "";
			
			while((line = br.readLine()) != null) {
				
				result += line;
			}
					
			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(result);
			
			JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
			JsonObject kakaoAccount = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
			
			String nickname = properties.getAsJsonObject().get("nickname").getAsString();
			String email = kakaoAccount.getAsJsonObject().get("email").getAsString();
			
			userInfo.put("nickname", nickname);
			userInfo.put("email", email);
		
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
		return userInfo;
	}

	public void kakaoLogout(String accessToken) {
		
		String reqURL = "https://kauth.kakao.com/oauth/logout?client_id=b05da66ce6b812c049b788547193fbdc&logout_redirect_uri=http://localhost/momolearn/member/sessionOut";
		
		try {
			
			URL url = new URL(reqURL);
			
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Authorization", "Bearer " + accessToken);
			
		} catch (Exception e) {
			
			throw new RuntimeException("로그아웃 요청 실패: " + e.getMessage());
			
		}
	}

}
