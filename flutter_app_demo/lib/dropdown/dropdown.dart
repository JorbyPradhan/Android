import 'package:flutter/material.dart';

void main() {
  runApp(myDropDown());
}

class myDropDown extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: "Drop Down",
      theme: ThemeData(
        primarySwatch: Colors.red,
      ),
      home: MyDrop(),
    );
  }
}

class MyDrop extends StatefulWidget {
  @override
  _MyDropState createState() => _MyDropState();
}

class _MyDropState extends State<MyDrop> {
  String value = "";
  List<DropdownMenuItem<String>> menuItems = List();
  bool disabledropdown = true;

  final web = {
    "1": "PHP",
    "2": "Python",
    "3": "Node JS",
  };
  final app = {
    "1": "Java",
    "2": "Kotlin",
    "3": "Flutter",
  };
  final desktop = {
    "1": "JavaFx",
    "2": "Tkinter",
    "3": "Electron",
  };

  void populateweb() {
  /*  for (String key in web.keys) {
      menuItems.add(DropdownMenuItem<String>(
        child: Center(
          child: Text(
              web[key]
          ),
        ),
        value = web[key],
      ),
      );*/
   // }
  }

  void selected(_value) {
    if (_value == "Web") {
      populateweb();
    }
    setState(() {
      value = _value;
      disabledropdown = false;
    });
  }

  void secondselected(_value) {
    setState(() {
      value = _value;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(
            "Dropdown"
        ),
        centerTitle: true,
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.spaceEvenly,
          children: <Widget>[
            DropdownButton<String>(
              items: [
                DropdownMenuItem<String>(
                  value: "App",
                  child: Center(
                    child: Text("App"),
                  ),
                ),
                DropdownMenuItem<String>(
                  value: "Web",
                  child: Center(
                    child: Text("Web"),
                  ),
                ), DropdownMenuItem<String>(
                  value: "Desktop",
                  child: Center(
                    child: Text("Desktop"),
                  ),
                ),
              ],
              onChanged: (_value) => selected(_value),
              hint: Text(
                "Select Your Filed",
              ),
            ),
            Text(
              "$value",
              style: TextStyle(
                fontSize: 20.0,
              ),
            ),
            DropdownButton<String>(
              items: [
                DropdownMenuItem<String>(
                  value: "App",
                  child: Center(
                    child: Text("App"),
                  ),
                ),
                DropdownMenuItem<String>(
                  value: "Web",
                  child: Center(
                    child: Text("Web"),
                  ),
                ), DropdownMenuItem<String>(
                  value: "Desktop",
                  child: Center(
                    child: Text("Desktop"),
                  ),
                ),
              ],
              onChanged: (_value) {
                setState(() {
                  value = _value;
                });
              },
              hint: Text(
                "Select Your Filed",
              ),
            ),
            Text(
              "$value",
              style: TextStyle(
                fontSize: 20.0,
              ),
            ),
            DropdownButton<String>(
              items: [
                DropdownMenuItem<String>(
                  value: "App",
                  child: Center(
                    child: Text("App"),
                  ),
                ),
                DropdownMenuItem<String>(
                  value: "Web",
                  child: Center(
                    child: Text("Web"),
                  ),
                ), DropdownMenuItem<String>(
                  value: "Desktop",
                  child: Center(
                    child: Text("Desktop"),
                  ),
                ),
              ],
              onChanged: (_value) {
                setState(() {
                  value = _value;
                });
              },
              hint: Text(
                "Select Your Filed",
              ),
            ),
            Text(
              "$value",
              style: TextStyle(
                fontSize: 20.0,
              ),
            ),
          ],
        ),
      ),
    );
  }
}

