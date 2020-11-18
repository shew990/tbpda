package com.example.blue;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.example.mylibrary.LPK130;

import java.io.UnsupportedEncodingException;

//import com.sdk.FUPrinter.LPK130;
//import com.sdk.FUPrinter.NFCP_Printer;


public class blueActivity extends AppCompatActivity {


    protected static final String TAG = "BluetoothChat";
    protected static final boolean D = true;
    // 从 BluetoothChatService 处理程序发送的消息类型
    // Message types sent from the BluetoothChatService Handler
    public static final int MESSAGE_STATE_CHANGE = 1;
    public static final int MESSAGE_READ = 2;
    public static final int MESSAGE_WRITE = 3;
    public static final int MESSAGE_DEVICE_NAME = 4;
    public static final int MESSAGE_TOAST = 5;
    // BluetoothChatService 处理程序收到的键名
    // Key names received from the BluetoothChatService Handler
    public static final String DEVICE_NAME = "device_name";
    public static final String TOAST = "toast";

    // Intent request codes意向请求代码
    protected static final int REQUEST_CONNECT_DEVICE = 1;
    protected static final int REQUEST_ENABLE_BT = 2;

    public String strBtAddr = null;



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (D)
            Log.d(TAG, "onActivityResult " + resultCode);

        switch (requestCode) {
            case REQUEST_CONNECT_DEVICE:
                if (resultCode == Activity.RESULT_OK) {

                    strBtAddr = data.getExtras().getString(
                            DeviceListActivity.EXTRA_DEVICE_ADDRESS);

                    Toast.makeText(this, "address--->" + strBtAddr,
                            Toast.LENGTH_SHORT).show();

                }
                break;
            case REQUEST_ENABLE_BT:

                if (resultCode == Activity.RESULT_OK) {

                } else {
                    Log.d(TAG, "BT not enabled");
                    Toast.makeText(this, R.string.bt_not_enabled_leaving,
                            Toast.LENGTH_SHORT).show();
                    finish();
                }
        }
    }

    class myclick implements OnClickListener {
        @Override
        public void onClick(View arg0) {
            // TODO Auto-generated method stub
            // String strBtAddr=btAddrtxt.getText().toString();
            if (strBtAddr == null) {

                return;
            }
            int i = arg0.getId();
            if (i == R.id.lpk130) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        LPK130DEMO();
                    }
                }).start();
                //                case R.id.lpk130_line_tag:
//                    new Thread(new Runnable() {
//                        @Override
//                        public void run() {
//                            LPK130DEMO_LINE_TEST();
//                        }
//                    }).start();
//                    break;
            } else if (i == R.id.mpk1800) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        MPK1800DEMO();
                    }
                }).start();
            }

        }
    }

    //lpk130打印示例
    public void LPK130DEMO() {
        LPK130 lpk130 = new LPK130();
        lpk130.closeDevice();
        if (lpk130.openDevice(strBtAddr) >= 0) {
            try {
                lpk130.NFCP_feed(5);
                lpk130.NFCP_setLeftMargin((byte) 12);
                lpk130.NFCP_setSnapMode((byte) 1);
                lpk130.NFCP_setLineSpace(31);
                lpk130.NFCP_setFontBold((byte) 1);
                lpk130.NFCP_printStrLine("**市公安局**分局交通警察支队");
                lpk130.NFCP_printStrLine("公安交通管理简易程序处罚决定书");
                lpk130.NFCP_setFontBold((byte) 0);
                lpk130.NFCP_fontSize((byte) 1, (byte) 1);
                lpk130.NFCP_printStrLine("第一联（留存）");
                lpk130.NFCP_feed(20);
                lpk130.NFCP_drawBarCode("*123456789*", 1, 100, 2);
                lpk130.NFCP_feed(20);
                lpk130.NFCP_setSnapMode((byte) 0);
                lpk130.NFCP_printStrLine("编号：1000005088    执法机关代码： 3109018");
                lpk130.NFCP_printStr("被处罚人：");
                lpk130.NFCP_setUnderLine((byte) 2);
                lpk130.NFCP_printStrLine("罗**");
                lpk130.NFCP_setUnderLine((byte) 0);
                lpk130.NFCP_printStr("机动车驾驶证档案编号：");
                lpk130.NFCP_setUnderLine((byte) 2);
                lpk130.NFCP_printStrLine("3000010005237");
                lpk130.NFCP_setUnderLine((byte) 0);
                lpk130.NFCP_printStrLine("机动车驾驶证");
                lpk130.NFCP_printStr("或居民身份证号码：");

                lpk130.NFCP_setUnderLine((byte) 2);
                lpk130.NFCP_printStrLine("30900117890203013213");
                lpk130.NFCP_setUnderLine((byte) 0);
                lpk130.NFCP_printStr("准驾车型：");
                lpk130.NFCP_setUnderLine((byte) 2);
                lpk130.NFCP_printStrLine("C1E");
                lpk130.NFCP_setUnderLine((byte) 0);
                lpk130.NFCP_printStr("联系方式：");
                lpk130.NFCP_setUnderLine((byte) 2);
                lpk130.NFCP_printStrLine("**市**区**村49号35室");
                lpk130.NFCP_setUnderLine((byte) 0);
                lpk130.NFCP_printStr("车辆牌号：");
                lpk130.NFCP_setUnderLine((byte) 2);
                lpk130.NFCP_printStrLine("*E29146");
                lpk130.NFCP_setUnderLine((byte) 0);
                lpk130.NFCP_printStr("车辆类型：");
                lpk130.NFCP_setUnderLine((byte) 2);
                lpk130.NFCP_printStrLine("轿车");
                lpk130.NFCP_setUnderLine((byte) 0);
                lpk130.NFCP_printStr("发证机关：");
                lpk130.NFCP_setUnderLine((byte) 2);
                lpk130.NFCP_printStrLine("**市");
                lpk130.NFCP_setUnderLine((byte) 0);
                lpk130.NFCP_feed(32);
                lpk130.NFCP_printStr("    被处罚人于");
                lpk130.NFCP_setUnderLine((byte) 2);
                lpk130.NFCP_printStr("2013");
                lpk130.NFCP_setUnderLine((byte) 0);
                lpk130.NFCP_printStr("年");
                lpk130.NFCP_setUnderLine((byte) 2);
                lpk130.NFCP_printStr("04");
                lpk130.NFCP_setUnderLine((byte) 0);
                lpk130.NFCP_printStr("月");
                lpk130.NFCP_setUnderLine((byte) 2);
                lpk130.NFCP_printStr("03");
                lpk130.NFCP_setUnderLine((byte) 0);
                lpk130.NFCP_printStr("日");
                lpk130.NFCP_setUnderLine((byte) 2);
                lpk130.NFCP_printStr("17");
                lpk130.NFCP_setUnderLine((byte) 0);
                lpk130.NFCP_printStr("时");
                lpk130.NFCP_setUnderLine((byte) 2);
                lpk130.NFCP_printStr("15");
                lpk130.NFCP_setUnderLine((byte) 0);
                lpk130.NFCP_printStr("分");
                lpk130.NFCP_printStr("，在");
                lpk130.NFCP_setUnderLine((byte) 2);
                lpk130.NFCP_printStr("**公路和**路的交叉路口约200米");
                lpk130.NFCP_setUnderLine((byte) 0);
                lpk130.NFCP_printStr("实施");
                lpk130.NFCP_setUnderLine((byte) 2);
                lpk130.NFCP_printStr("通过路口遇放\r\n行信号不依次通过的（抢道）违法行为（代码\r\n1210）");
                lpk130.NFCP_setUnderLine((byte) 0);
                lpk130.NFCP_printStrLine(" ，违反了《中华人民共和国道路交通安全》\n" +
                        "第九十条的规定。");
                lpk130.NFCP_feed(30);
                lpk130.NFCP_printStrLine("    根据《中华人民共和国道路交通安全法实施条\n" +
                        "例》第五十一条第四项，决定给予：");
                lpk130.NFCP_feed(30);
                lpk130.NFCP_setUnderLine((byte) 1);
                lpk130.NFCP_chooseFont((byte) 1);
                lpk130.NFCP_setSnapMode((byte) 1);
                lpk130.NFCP_fontSize((byte) 2, (byte) 2);
                lpk130.NFCP_printStrLine("壹佰圆（￥100）罚款");
                lpk130.NFCP_fontSize((byte) 1, (byte) 1);
                lpk130.NFCP_setUnderLine((byte) 0);
                lpk130.NFCP_setFontBold((byte) 0);
                lpk130.NFCP_chooseFont((byte) 0);
                lpk130.NFCP_setSnapMode((byte) 0);
                lpk130.NFCP_feed(40);
                lpk130.NFCP_printStrLine("    持本决定书在15日内到中国工商银行代收点\r\n（地点：**路8938号，时间：周一至周六、8：\r\n30-16：30）缴纳罚款。逾期不缴纳的，每日按罚款\r\n数额的3%加处罚款。");
                lpk130.NFCP_feed(30);
                lpk130.NFCP_printStrLine("    如不服本决定，可以在收到本决定之日起60日\r\n内依法向**区公安分局或**区人民政府申请行\r\n政复议；或者在3个月内向青浦区人民法院提起行政\r\n诉讼。   ");
                lpk130.NFCP_feed(30);

                lpk130.NFCP_printStrLine("处罚地点：**公路和**路的交叉路口");
                lpk130.NFCP_feed(30);
                lpk130.NFCP_printStrLine("警号：999999   ");
                lpk130.NFCP_printStrLine("交通警察：");
                lpk130.NFCP_printStrLine("                   **市公安局**分局");
                lpk130.NFCP_printStrLine("                       交通警察支队 ");
                lpk130.NFCP_printStrLine("                   日期：2013年03月17日");
                lpk130.NFCP_feed(60);
                lpk130.NFCP_printStrLine("被处罚人签名：");
                lpk130.NFCP_feed(60);

                lpk130.NFCP_printStr("签收日期：");
                lpk130.NFCP_setUnderLine((byte) 2);
                lpk130.NFCP_printStr("        ");
                lpk130.NFCP_setUnderLine((byte) 0);
                lpk130.NFCP_printStr("年");
                lpk130.NFCP_setUnderLine((byte) 2);
                lpk130.NFCP_printStr("  ");
                lpk130.NFCP_setUnderLine((byte) 0);
                lpk130.NFCP_printStr("月");
                lpk130.NFCP_setUnderLine((byte) 2);
                lpk130.NFCP_printStr("  ");
                lpk130.NFCP_setUnderLine((byte) 0);
                lpk130.NFCP_printStrLine("日");


                lpk130.NFCP_printStrLine("备注：");
                lpk130.NFCP_feed(20);
                lpk130.NFCP_setUnderLine((byte) 2);
                lpk130.NFCP_printStrLine("                       ");
                lpk130.NFCP_setUnderLine((byte) 0);
                lpk130.NFCP_feed(20);
                lpk130.NFCP_printStrLine("根据《机动车驾驶证申领和使用规定》记0分");
                lpk130.NFCP_printStrLine("注：未作罚款处罚或者当场收缴罚款的，银行联不\r\n交被处罚人。");
                lpk130.NFCP_feed(160);

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "设备连接失败，请重新连接！",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void MPK1800DEMO() {
        LPK130 mpk1800 = new LPK130();
        if (mpk1800.openDevice(strBtAddr) >= 0) {
            try {
                mpk1800.NFCP_feed(5);
                mpk1800.NFCP_setLeftMargin((byte) 12);
                mpk1800.NFCP_setSnapMode((byte) 1);
                mpk1800.NFCP_setLineSpace(31);
                mpk1800.NFCP_setFontBold((byte) 1);
                mpk1800.NFCP_printStrLine("**市公安局**分局交通警察支队");
                mpk1800.NFCP_printStrLine("公安交通管理简易程序处罚决定书");
                mpk1800.NFCP_setFontBold((byte) 0);
                mpk1800.NFCP_fontSize((byte) 1, (byte) 1);
                mpk1800.NFCP_printStrLine("第一联（留存）");
                mpk1800.NFCP_feed(20);
                mpk1800.NFCP_drawBarCode("*123456789*", 1, 100, 2);
                mpk1800.NFCP_feed(20);
                mpk1800.NFCP_setSnapMode((byte) 0);
                mpk1800.NFCP_printStrLine("编号：1000005088    执法机关代码： 3109018");
                mpk1800.NFCP_printStr("被处罚人：");
                mpk1800.NFCP_setUnderLine((byte) 2);
                mpk1800.NFCP_printStrLine("罗**");
                mpk1800.NFCP_setUnderLine((byte) 0);
                mpk1800.NFCP_printStr("机动车驾驶证档案编号：");
                mpk1800.NFCP_setUnderLine((byte) 2);
                mpk1800.NFCP_printStrLine("3000010005237");
                mpk1800.NFCP_setUnderLine((byte) 0);
                mpk1800.NFCP_printStrLine("机动车驾驶证");
                mpk1800.NFCP_printStr("或居民身份证号码：");

                mpk1800.NFCP_setUnderLine((byte) 2);
                mpk1800.NFCP_printStrLine("30900117890203013213");
                mpk1800.NFCP_setUnderLine((byte) 0);
                mpk1800.NFCP_printStr("准驾车型：");
                mpk1800.NFCP_setUnderLine((byte) 2);
                mpk1800.NFCP_printStrLine("C1E");
                mpk1800.NFCP_setUnderLine((byte) 0);
                mpk1800.NFCP_printStr("联系方式：");
                mpk1800.NFCP_setUnderLine((byte) 2);
                mpk1800.NFCP_printStrLine("**市**区**村49号35室");
                mpk1800.NFCP_setUnderLine((byte) 0);
                mpk1800.NFCP_printStr("车辆牌号：");
                mpk1800.NFCP_setUnderLine((byte) 2);
                mpk1800.NFCP_printStrLine("*E29146");
                mpk1800.NFCP_setUnderLine((byte) 0);
                mpk1800.NFCP_printStr("车辆类型：");
                mpk1800.NFCP_setUnderLine((byte) 2);
                mpk1800.NFCP_printStrLine("轿车");
                mpk1800.NFCP_setUnderLine((byte) 0);
                mpk1800.NFCP_printStr("发证机关：");
                mpk1800.NFCP_setUnderLine((byte) 2);
                mpk1800.NFCP_printStrLine("**市");
                mpk1800.NFCP_setUnderLine((byte) 0);
                mpk1800.NFCP_feed(32);
                mpk1800.NFCP_printStr("    被处罚人于");
                mpk1800.NFCP_setUnderLine((byte) 2);
                mpk1800.NFCP_printStr("2013");
                mpk1800.NFCP_setUnderLine((byte) 0);
                mpk1800.NFCP_printStr("年");
                mpk1800.NFCP_setUnderLine((byte) 2);
                mpk1800.NFCP_printStr("04");
                mpk1800.NFCP_setUnderLine((byte) 0);
                mpk1800.NFCP_printStr("月");
                mpk1800.NFCP_setUnderLine((byte) 2);
                mpk1800.NFCP_printStr("03");
                mpk1800.NFCP_setUnderLine((byte) 0);
                mpk1800.NFCP_printStr("日");
                mpk1800.NFCP_setUnderLine((byte) 2);
                mpk1800.NFCP_printStr("17");
                mpk1800.NFCP_setUnderLine((byte) 0);
                mpk1800.NFCP_printStr("时");
                mpk1800.NFCP_setUnderLine((byte) 2);
                mpk1800.NFCP_printStr("15");
                mpk1800.NFCP_setUnderLine((byte) 0);
                mpk1800.NFCP_printStr("分");
                mpk1800.NFCP_printStr("，在");
                mpk1800.NFCP_setUnderLine((byte) 2);
                mpk1800.NFCP_printStr("**公路和**路的交叉路口约200米");
                mpk1800.NFCP_setUnderLine((byte) 0);
                mpk1800.NFCP_printStr("实施");
                mpk1800.NFCP_setUnderLine((byte) 2);
                mpk1800.NFCP_printStr("通过路口遇放\r\n行信号不依次通过的（抢道）违法行为（代码\r\n1210）");
                mpk1800.NFCP_setUnderLine((byte) 0);
                mpk1800.NFCP_printStrLine(" ，违反了《中华人民共和国道路交通安全》\n" +
                        "第九十条的规定。");
                mpk1800.NFCP_feed(30);
                mpk1800.NFCP_printStrLine("    根据《中华人民共和国道路交通安全法实施条\n" +
                        "例》第五十一条第四项，决定给予：");
                mpk1800.NFCP_feed(30);
                mpk1800.NFCP_setUnderLine((byte) 1);
                mpk1800.NFCP_chooseFont((byte) 1);
                mpk1800.NFCP_setSnapMode((byte) 1);
                mpk1800.NFCP_fontSize((byte) 2, (byte) 2);
                mpk1800.NFCP_printStrLine("壹佰圆（￥100）罚款");
                mpk1800.NFCP_fontSize((byte) 1, (byte) 1);
                mpk1800.NFCP_setUnderLine((byte) 0);
                mpk1800.NFCP_setFontBold((byte) 0);
                mpk1800.NFCP_chooseFont((byte) 0);
                mpk1800.NFCP_setSnapMode((byte) 0);
                mpk1800.NFCP_feed(40);
                mpk1800.NFCP_printStrLine("    持本决定书在15日内到中国工商银行代收点\r\n（地点：**路8938号，时间：周一至周六、8：\r\n30-16：30）缴纳罚款。逾期不缴纳的，每日按罚款\r\n数额的3%加处罚款。");
                mpk1800.NFCP_feed(30);
                mpk1800.NFCP_printStrLine("    如不服本决定，可以在收到本决定之日起60日\r\n内依法向**区公安分局或**区人民政府申请行\r\n政复议；或者在3个月内向青浦区人民法院提起行政\r\n诉讼。   ");
                mpk1800.NFCP_feed(30);

                mpk1800.NFCP_printStrLine("处罚地点：**公路和**路的交叉路口");
                mpk1800.NFCP_feed(30);
                mpk1800.NFCP_printStrLine("警号：999999   ");
                mpk1800.NFCP_printStrLine("交通警察：");
                mpk1800.NFCP_printStrLine("                   **市公安局**分局");
                mpk1800.NFCP_printStrLine("                       交通警察支队 ");
                mpk1800.NFCP_printStrLine("                   日期：2013年03月17日");
                mpk1800.NFCP_feed(60);
                mpk1800.NFCP_printStrLine("被处罚人签名：");
                mpk1800.NFCP_feed(60);

                mpk1800.NFCP_printStr("签收日期：");
                mpk1800.NFCP_setUnderLine((byte) 2);
                mpk1800.NFCP_printStr("        ");
                mpk1800.NFCP_setUnderLine((byte) 0);
                mpk1800.NFCP_printStr("年");
                mpk1800.NFCP_setUnderLine((byte) 2);
                mpk1800.NFCP_printStr("  ");
                mpk1800.NFCP_setUnderLine((byte) 0);
                mpk1800.NFCP_printStr("月");
                mpk1800.NFCP_setUnderLine((byte) 2);
                mpk1800.NFCP_printStr("  ");
                mpk1800.NFCP_setUnderLine((byte) 0);
                mpk1800.NFCP_printStrLine("日");


                mpk1800.NFCP_printStrLine("备注：");
                mpk1800.NFCP_feed(20);
                mpk1800.NFCP_setUnderLine((byte) 2);
                mpk1800.NFCP_printStrLine("                       ");
                mpk1800.NFCP_setUnderLine((byte) 0);
                mpk1800.NFCP_feed(20);
                mpk1800.NFCP_printStrLine("根据《机动车驾驶证申领和使用规定》记0分");
                mpk1800.NFCP_printStrLine("注：未作罚款处罚或者当场收缴罚款的，银行联不\r\n交被处罚人。");
                mpk1800.NFCP_feed(20);

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "设备连接失败，请重新连接！",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void LPK130DEMO_LINE() {
        LPK130 lpk130 = new LPK130();
        lpk130.closeDevice();
        if (lpk130.openDevice(strBtAddr) >= 0) {
            lpk130.NFCP_createPage(576, 400);//起始设置
            lpk130.NFCP_Page_drawLine(0, 0, 575, 0);//上边框
            lpk130.NFCP_Page_drawLine(0, 0, 0, 378);//左边框
            lpk130.NFCP_Page_drawLine(575, 0, 575, 378);//右边框
            lpk130.NFCP_Page_drawLine(0, 378, 575, 378);//下边框

            int fontHeight = 24;
            int currentY = 2;
            try {
                lpk130.NFCP_Page_setText(26, currentY, "客户信息：", 2, 0, 1, false, false);
                lpk130.NFCP_Page_setText(405, currentY, "备注：", 2, 0, 1, false, false);
                currentY += fontHeight;
                lpk130.NFCP_Page_drawBar(40, currentY, "VA328991892789", 1, 0, 2, 60);//
                currentY += 60;
                lpk130.NFCP_Page_setText(80, currentY, "VA328991892789", 2, 0, 1, false, false);
                currentY += fontHeight + 6;
                lpk130.NFCP_Page_drawLine(0, currentY, 400, currentY);
                lpk130.NFCP_Page_setText(20, currentY, "增值", 2, 0, 1, false, false);
                lpk130.NFCP_Page_setText(140, currentY, "价保：", 2, 0, 1, false, false);
                lpk130.NFCP_Page_setText(260, currentY, "代收货款：", 2, 0, 1, false, false);
                currentY += fontHeight + 6;
                lpk130.NFCP_Page_drawLine(400, 0, 400, currentY);
                lpk130.NFCP_Page_drawLine(0, currentY, 576, currentY);
                lpk130.NFCP_Page_setText(20, currentY, "寄方：", 2, 0, 1, false, false);
                lpk130.NFCP_Page_setText(140, currentY, "南京市 栖霞区 仙新中路1号", 2, 0, 1, false, false);
                currentY += fontHeight + 6;
                lpk130.NFCP_Page_setText(20, currentY, "信息", 2, 0, 1, false, false);
                lpk130.NFCP_Page_setText(140, currentY, "郑爽 13928975638", 2, 0, 1, false, false);
                currentY += currentY + 6;
                lpk130.NFCP_Page_drawLine(120, 116, 120, currentY);
                lpk130.NFCP_Page_drawLine(0, currentY, 576, currentY);
                currentY += 28;
                lpk130.NFCP_Page_setText(20, currentY, "付款方式：寄付月结", 2, 0, 1, false, false);
                lpk130.NFCP_Page_setText(300, currentY, "商家订单：123987654789", 2, 0, 1, false, false);
//                currentY+=52;
//                lpk130.NFCP_Page_drawLine(0,currentY,576,currentY);
//                lpk130.NFCP_Page_drawLine(288,234,288,currentY);
//                lpk130.NFCP_Page_drawLine(380,currentY,380,478);

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            lpk130.NFCP_printPage(0, 1);
        }
    }

    public void LPK130DEMO_LINE_TAG() {
        LPK130 lpk130 = new LPK130();
        lpk130.closeDevice();
        if (lpk130.openDevice(strBtAddr) >= 0) {
            int height = 383;
            lpk130.NFCP_createPage(576, height + 1);//起始设置
            lpk130.NFCP_Page_drawLine(0, 0, 575, 0);//上边框
            lpk130.NFCP_Page_drawLine(0, 0, 0, height);//左边框
            lpk130.NFCP_Page_drawLine(575, 0, 575, height);//右边框
            lpk130.NFCP_Page_drawLine(0, height, 575, height);//下边框

            lpk130.NFCP_printPage(0, 1);
        }
    }
    public void LPK130DEMO_LINE_TEST() {
        LPK130 lpk130 = new LPK130();
        lpk130.closeDevice();
        if (lpk130.openDevice(strBtAddr) >= 0) {
            int height = 449;
            lpk130.NFCP_createPage(576, height + 1);//起始设置
            lpk130.NFCP_Page_drawLine(0, 0, 575, 0);//上边框
            lpk130.NFCP_Page_drawLine(0, 0, 0, height);//左边框
            lpk130.NFCP_Page_drawLine(575, 0, 575, height);//右边框
            lpk130.NFCP_Page_drawLine(0, height, 575, height);//下边框

            try {
                lpk130.NFCP_Page_setText(26, 2, "中骅物流单页测试", 2, 0, 1, false, false);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            lpk130.NFCP_printPage(0, 1);
        }
    }
}