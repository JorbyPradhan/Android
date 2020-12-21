import 'package:flutter/material.dart';

void main() {
  runApp(BottomNavBar());
}

class BottomNavBar extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: "Bottom Navigation",
      theme: ThemeData(
        primarySwatch: Colors.red,
      ),
      home: MyNavBar(),
    );
  }
}

class MyNavBar extends StatefulWidget {
  @override
  _MyNavBarState createState() => _MyNavBarState();
}

class _MyNavBarState extends State<MyNavBar> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Bottom Navigation"),
      ),
      bottomNavigationBar: BottomAppBar(
        child: Row(
          children: <Widget>[
            Expanded(
              child: SizedBox(
                height: 60.0,
                child: InkWell(
                  onTap: () {},
                  child: Column(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: <Widget>[
                      Icon(
                        Icons.search,
                      ),
                      Text("Search"),
                    ],
                  ),
                ),
              ),
            ),
            Expanded(
                child: SizedBox(
              height: 60.0,
              child: InkWell(
                onTap: () {},
                child: Column(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: <Widget>[
                    Icon(
                      Icons.search,
                    ),
                    Text("Search"),
                  ],
                ),
              ),
            ),),
            Expanded(
                child: SizedBox(
              height: 60.0,
              child: InkWell(
                onTap: () {},
                child: Column(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: <Widget>[
                    Icon(
                      Icons.search,
                    ),
                    Text("Search"),
                  ],
                ),
              ),
            ),),
          ],
        ),
      ),
    );
  }
}
