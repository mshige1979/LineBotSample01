package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.example.message.LineBotRequest;
import com.example.message.MessageLocation;
import com.example.message.MessageTemplate;
import com.example.message.MessageText;
import com.example.message.template.Buttons;
import com.example.message.template.Carousel;
import com.example.message.template.Confirm;
import com.example.message.template.actions.Actions;
import com.example.message.template.column.Column;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.entity.StringEntity;
import com.fasterxml.jackson.annotation.JsonInclude;


public class Sample01 {

    // アクセストークン
	static String access_token = "JoGPMXrT0qrP/20D2d33TfFBeZPKF5o8tBL59S4id+NY1VjHV5fg2wCOs97f9D9aiLc44id96Xdn6niFY7oo2M1UTjJSb6OrPO43gim7M6pRvMdps6RWv9zuB9n08t9B8+xpoFV4vCE4+7P5ijLungdB04t89/1O/w1cDnyilFU=";

	// シークレットアクセストークン
	static String channel_secret = "aec190a3839462a454906032f834adf4";
	
	public static void main(String[] args) throws JsonProcessingException {
	
		Sample01 sample = new Sample01();
		sample.send();

	}
	
	public void send() throws JsonProcessingException {
		
		String TO = "U2bf985068dbfef16569d981d24602eba";
		
		LineBotRequest lineReq = null;
		
        // テキスト
		MessageText message1 = new MessageText();
		message1.type = "text";
		message1.text = "hogehoge";
        lineReq = new LineBotRequest();
        lineReq.to = TO;
        lineReq.messages.add(message1);
        sendMessage(lineReq);
        
        // 位置
        MessageLocation  message2 = new MessageLocation();
        message2.type = "location";
        message2.title = "my location";
        message2.address = "〒150-0002 東京都渋谷区渋谷２丁目２１−１";
        message2.latitude = 35.65910807942215;
        message2.longitude = 139.70372892916203;
        lineReq = new LineBotRequest();
        lineReq.to = TO;
        lineReq.messages.add(message2);
        sendMessage(lineReq);
        
        // ボタン
        MessageTemplate message3 = new MessageTemplate();
        List<Actions> actions = new ArrayList<Actions>();
        actions.add(new Actions("sample1", "sample111です"));
        actions.add(new Actions("sample2", "sample222です"));
        Buttons buttons = new Buttons();
        //buttons.title = "menu";
        buttons.text = "sample";
        buttons.actions = actions;
        message3.type = "template";
        message3.altText = "test";
        message3.template = buttons;
        lineReq = new LineBotRequest();
        lineReq.to = TO;
        lineReq.messages.add(message3);
        sendMessage(lineReq);
        
        // コンファーム
        MessageTemplate message4 = new MessageTemplate();
        List<Actions> actions2 = new ArrayList<Actions>();
        actions2.add(new Actions("sample3", "sample333です"));
        actions2.add(new Actions("sample4", "sample444です"));
        Confirm confirm = new Confirm();
        confirm.text = "sample";
        confirm.actions = actions2;
        message4.type = "template";
        message4.altText = "test";
        message4.template = confirm;
        lineReq = new LineBotRequest();
        lineReq.to = TO;
        lineReq.messages.add(message4);
        sendMessage(lineReq);
        
        // カルーセル
        MessageTemplate message5 = new MessageTemplate();
        List<Actions> actions3 = new ArrayList<Actions>();
        actions3.add(new Actions("sample5", "sample555です"));
        actions3.add(new Actions("sample6", "sample666です"));
        List<Column> columns = new ArrayList<Column>();
        columns.add(new Column("foo1", actions3));
        columns.add(new Column("foo2", actions3));
        Carousel carousel = new Carousel();
        carousel.type = "carousel";
        carousel.columns = columns; 
        message5.type = "template";
        message5.altText = "test";
        message5.template = carousel;
        lineReq = new LineBotRequest();
        lineReq.to = TO;
        lineReq.messages.add(message5);
        sendMessage(lineReq);
	}
	
	private static void sendMessage(LineBotRequest lineReq) throws JsonProcessingException {
		
		String json = null ;
		
		// json変換用
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		
		json = mapper.writeValueAsString(lineReq);
		
		// 送信処理
        HttpPost httpPost = new HttpPost("https://api.line.me/v2/bot/message/push");    
        httpPost.setHeader("Content-Type", "application/json");
       	httpPost.setHeader("Authorization", "Bearer " + access_token);
        StringEntity params = new StringEntity(json, StandardCharsets.UTF_8);
        httpPost.setEntity(params);
        
        System.out.println(json);
        
        try (CloseableHttpClient client = HttpClients.createDefault();
                CloseableHttpResponse resp = client.execute(httpPost);
                BufferedReader br = new BufferedReader(new InputStreamReader(resp.getEntity().getContent(), StandardCharsets.UTF_8))) {
            
            int statusCode = resp.getStatusLine().getStatusCode();
            switch (statusCode) {
            case 200:
                // ↓これは空のJSON({})が返るはず
                System.out.println(br.readLine());
                break;
            default:
            	System.out.println(br.readLine());
            }
        } catch (final ClientProtocolException e) {
        } catch (final IOException e) {
        }
	}

}
