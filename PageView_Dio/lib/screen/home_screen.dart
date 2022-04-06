import 'dart:convert';

import 'package:dio/dio.dart';
import 'package:extended_image/extended_image.dart';
import 'package:flutter/material.dart';
import 'package:pageview_dio/const/data.dart';

class HomeScreen extends StatefulWidget {
  const HomeScreen({Key? key}) : super(key: key);

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

// http://api.openweathermap.org/data/2.5/weather?q=seoul&APPID=<api key>&lang=kr&units=metric

class _HomeScreenState extends State<HomeScreen> {

  List<String> urls = [];

  @override
  void initState() {
    fetchData();
    fetchImageData("dog");
    super.initState();
  }

// http://api.openweathermap.org/data/2.5/weather?q=seoul&APPID=<api key>&lang=kr&units=metric
  fetchData() async {
    final response = await Dio().get(
        'http://api.openweathermap.org/data/2.5/weather',
        queryParameters: {
          'APPID': serviceKey,
          'q': 'seoul',
          'lang': 'kr',
          'units': "metric",
        });
    print(response.data);
  }

  @override
  Widget build(BuildContext context) {
    return SafeArea(
      child: Scaffold(
        body: Column(
          children: [
            Row(
              children: [
                Expanded(child: Padding(
                  padding: const EdgeInsets.symmetric(horizontal: 8.0),
                  child: TextField(),
                )),
                IconButton(onPressed: () async {
                  await fetchImageData("soccer");
                }, icon: Icon(Icons.account_balance))
              ],
            ),
            Expanded(
              child: ListView.builder(
                itemBuilder: (BuildContext context, int index){
                  return ExtendedImage.network(urls[index]);
                },
                      itemCount: urls.length,
              ),
            ),
          ],
        ),
      ),
    );
  }

  Future<String> fetchImageData(String keyword) async {

    Map<String, dynamic> header = {"Authorization":"KakaoAK a903569250932f8162a22f5de3258097"};

    var options = BaseOptions(
      headers: header,
    );

    final response = await Dio(options).get(
        'https://dapi.kakao.com/v2/search/image',
        queryParameters: {
          // "Authorization": "KakaoAK a903569250932f8162a22f5de3258097",
          'query': keyword,
          'sort': "recency",
          'page': 1,
          'size': 80,
        });
    print(response.data["documents"][0]['doc_url']);
    print(response.data['documents'].length);
    // var data = json.decode(response.data);
    // print(data);
    await addList(response);
    for(int i=0; i<80;i++){
      urls.add(response.data['documents'][i]['thumbnail_url']);
    }
    return "";
  }

  Future<void> addList(Response response) async {
    for(int i=0; i<80;i++){
      urls.add(response.data['documents'][i]['thumbnail_url']);
    }
  }

}
