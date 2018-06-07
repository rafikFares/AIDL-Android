/*
    packegae file need to be with the same name as the package name of the server
    this interface file need to be the exsact same file as the one in the server
*/

// IMainAidl.aidl
package com.example.XXXX.myaidlserverservice;

// Declare any non-default types here with import statements

interface IMainAidl {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */

     String getConcat(String val1, String val2);
     String getStringOf(int val);
     int getSum(int val1, int val2);
}
