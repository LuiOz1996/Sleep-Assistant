package com.example.sleepassiatantv04

object BluetoothDevice {

    fun getBluetoothDevice() : List<DataBluetooth>{
        return listOf(
            DataBluetooth(R.drawable.headphones,"AM61"),
            DataBluetooth(R.drawable.watch,"HUAWEI WATCH GT-421"),
            DataBluetooth(R.drawable.watch,"Ticwatch s 8D59"),
            DataBluetooth(R.drawable.headphones,"Toyota Touch"),
            DataBluetooth(R.drawable.headphones,"HUAWEI CM51")
        )
    }
}
