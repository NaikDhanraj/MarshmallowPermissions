# MarshmallowPermissions

Starting from Android Marshmallow (API 23), users will be asked for permissions while the app is running. This way, a user is able to choose which permissions they should grant without affecting the application flow

Permission Updates in Marshmallow

Marshmallow brought large changes to the permissions model. It introduced the concept of runtime permissions. These are permissions that are requested while the app is running (instead of before the app is installed). These permission can then be allowed or denied by the user. For approved permissions, these can also be revoked at a later time.
This means there are a couple more things to consider when working with permissions for a Marshmallow app. Keep in mind that your targetSdkVersion must be >= 23 and your emulator / device must be running Marshmallow to see the new permissions model. If this isn’t the case, see the backwards compatibility section to understand how permissions will behave on your configuration.

Normal Permissions

When you need to add a new permission, first check this page to see if the permission is considered a PROTECTION_NORMALpermission. In Marshmallow, Google has designated certain permissions to be “safe” and called these “Normal Permissions”. These are things like ACCESS_NETWORK_STATE, INTERNET, etc. which can’t do much harm. Normal permissions are automatically granted at install time and never prompt the user asking for permission.

Runtime Permissions 

If the permission you need to add isn’t listed under the normal permissions, you’ll need to deal with “Runtime Permissions”. Runtime permissions are permissions that are requested as they are needed while the app is running. These permissions will show a dialog to the user, similar to the following one:
The first step when adding a “Runtime Permission” is to add it to the AndroidManifest
