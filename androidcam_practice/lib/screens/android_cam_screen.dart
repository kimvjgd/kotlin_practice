import 'package:flutter/material.dart';

class AndroidCamScreen extends StatefulWidget {
  const AndroidCamScreen({Key? key}) : super(key: key);

  @override
  State<AndroidCamScreen> createState() => _AndroidCamScreenState();
}

class _AndroidCamScreenState extends State<AndroidCamScreen> {
  @override
  Widget build(BuildContext context) {
    return Container(
      child: ElevatedButton(onPressed: (){},child: Text('asdas'),),
    );
  }
}
