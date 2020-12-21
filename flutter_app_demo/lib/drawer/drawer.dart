import 'package:flutter/material.dart';
import 'package:flutter_app_demo/drawer/pagenew.dart';

class CutomDrawer extends StatefulWidget {
  @override
  _DrawerState createState() => _DrawerState();
}

class _DrawerState extends State<CutomDrawer> {
  @override
  Widget build(BuildContext context) {
    return Drawer(
      child: Column(
        mainAxisSize: MainAxisSize.max,
        children: <Widget>[
          UserAccountsDrawerHeader(
            accountName: Text(
              "James",
            ),
            accountEmail: Text(
              "jamespradhan@gmail.com",
            ),
            currentAccountPicture: CircleAvatar(
              child: Text(
                "P",
              ),
              backgroundColor: Colors.white,
            ),
            otherAccountsPictures: <Widget>[
              CircleAvatar(
                child: Text(
                  "P",
                ),
                backgroundColor: Colors.white,
              ),
            ],
            onDetailsPressed: (){},
          ),
          ListTile(
            leading: Icon(Icons.contacts),
            title: Text("Page"),
            trailing: Icon(Icons.contacts),
            onTap: () {
              Navigator.of(context).push(MaterialPageRoute(
                builder: (context) => PageNew(),
              ));
            },
            enabled: true,
            selected: true,
          ),
          ListTile(
            leading: Icon(Icons.contacts),
            title: Text("Contacts"),
            trailing: Icon(Icons.contacts),
            onTap: () => print("ListTile Tapped"),
            enabled: true,
            selected: true,
          ),
          Divider(),
          Expanded(
            child: Align(
              alignment: Alignment.bottomCenter,
              child:  ListTile(
                leading: Icon(Icons.bug_report),
                title: Text("Bugs"),
                onTap: () => print("ListTile Tapped"),
                enabled: true,
                selected: true,
              ),
            ),
          )
        ],
      ),
    );
  }
}
