package com.p2ild.vudinhson_abstractclass_interface;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class MainActivity extends Activity implements View.OnClickListener {

    private EditText edPoint1, edPoint2, edPoint3, edPoint4;
    private Button btTinhChuVi, btTinhDienTich;
    private RelativeLayout rl_flash_screen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);//Gọi các lệnh từ class cha mà class này kế thừa . Ở đây là class Activity(Nếu muốn chương trình có giao diện thì phải kế thừa class Activity này. Class này đã được thiết kế sẵn)
        setContentView(R.layout.activity_main);//View mà mình muốn hiển thị (Trong android view sẽ được thiét kế riêng trong thư mục res/layout và gọi vào code)
        initView();//Khởi tạo các view có trong giao diện
    }

    /*Khởi tạo các đối tượng cần xử lý trong View đã hiển thị lên*/
    private void initView() {
        /*Flash screen*/
        rl_flash_screen = (RelativeLayout) findViewById(R.id.rlFlashScreen);// Ánh xạ từ code giao diện vào code lập trình
        YoYo.with(Techniques.Landing).duration(4000).playOn(findViewById(R.id.tv_up));//Đặt hiệu ứng(animation)
        YoYo.with(Techniques.Landing).duration(4000).playOn(findViewById(R.id.tv_down));//Đặt hiệu ứng(animation)
        rl_flash_screen.setOnClickListener(this);//Bắt sự kiện khi người dùng click. This ở đây là chỉ Interface đã đc implement bên trên(View.OnClickListener)

        edPoint1 = (EditText) findViewById(R.id.edPoint1);//Ánh xạ editText để xử lý
        edPoint1.setInputType(InputType.TYPE_NULL);//Tắt keyboard khi chương trình mở
        edPoint2 = (EditText) findViewById(R.id.edPoint2);//Ánh xạ editText để xử lý
        edPoint3 = (EditText) findViewById(R.id.edPoint3);//Ánh xạ editText để xử lý
        edPoint4 = (EditText) findViewById(R.id.edPoint4);//Ánh xạ editText để xử lý
    }

    /*Code khi click vào button Nhập hình vuông*/
    public void onClickNhapHVuong(View v) {
        edPoint1.setText("0,0");
        edPoint2.setText("0,5");
        edPoint3.setText("5,5");
        edPoint4.setText("5,0");
    }

    /*Code khi click vào button Nhập hình chữ nhật*/
    public void onClickNhapHChuNhat(View v) {
        edPoint1.setText("0,0");
        edPoint2.setText("0,5");
        edPoint3.setText("10,5");
        edPoint4.setText("10,0");
    }

    /*Vì một đối tượng point có 2 tọa độ là x và y
    * Vì nhập vào theo cú pháp tungdo,hoanhdo
    * Nên ở phương thức này ta sẽ tách ra x riêng và y riêng từ phần nhập vào
    * Phương thức này trả về cho chúng ta một đối tượng point đã có tung độ và hoành độ
    * */
    public Point splitData(String dataInput) {
        try {
            int x = Integer.parseInt(dataInput.split(",")[0]);//tách theo điều kiện là dấu , ta được 2 phần tử. Lấy phần tử thứ 0 sẽ là tung độ
            int y = Integer.parseInt(dataInput.split(",")[1]);//tách theo điều kiện là dấu , ta được 2 phần tử. Lấy phần tử thứ 1 sẽ là hoành độ
            Point point = new Point(x, y);
            return point;
        } catch (NumberFormatException e) {
            //try catch ở đây bắt lỗi nếu người dùng cố tình nhập chữ vào ta sẽ thông báo lỗi input
        }
        return null;
    }

    public void onClickTinhChuVi(View v) {
        /*Lấy ra point từ các editText*/
        Point point1 = splitData(edPoint1.getText().toString());//Lấy ra point từ editPoint1
        Point point2 = splitData(edPoint2.getText().toString());//Lấy ra point từ editPoint2
        Point point3 = splitData(edPoint3.getText().toString());//Lấy ra point từ editPoint3
        Point point4 = splitData(edPoint4.getText().toString());//Lấy ra point từ editPoint4

        /*TÍnh độ dài 4 cạnh*/
        if (point1 != null && point2 != null && point3 != null && point4 != null) {
            double canhA = TuGiacAbstract.tinhDoDaiCanhKhiBietToaDo2Diem(point1, point2);
            double canhB = TuGiacAbstract.tinhDoDaiCanhKhiBietToaDo2Diem(point2, point3);
            double canhC = TuGiacAbstract.tinhDoDaiCanhKhiBietToaDo2Diem(point3, point4);
            double canhD = TuGiacAbstract.tinhDoDaiCanhKhiBietToaDo2Diem(point4, point1);

            /*Nếu các cạnh thỏa mãn một hình chữ nhật thì vào đoạn if này*/
            if (canhA == canhC && canhB == canhD && canhB != canhC) {
                /*Khởi tạo một đối tượng HinhChuNhatChild.
                * Vì kế thừa class TuGiacAbstract nên có thể khai báo là :
                 * C1: TuGiacAbstract hcn = new HinhChuNhatChild(canhA, canhB, canhC, canhD);
                 * C2 : HinhChuNhatChild hcn = new HinhChuNhatChild(canhA, canhB, canhC, canhD);*/
                TuGiacAbstract hcn = new HinhChuNhatChild(canhA, canhB, canhC, canhD);
                hcn.tinhChuVi();
                hcn.hienThiChuVi(this);
            }
            /* Còn nếu các cạnh thỏa mãn một hình vuông thì vào đoạn if này*/
            else if (canhA == canhB && canhB == canhC) {
                TuGiacAbstract hv = new HinhVuongChild(canhA, canhB, canhC, canhD);
                hv.tinhChuVi();
                hv.hienThiChuVi(this);
            }
            /*Nếu các cạnh không thỏa mãn hình vuông hoặc hình chữ nhật thì vào đoạn else này*/
            else {
                Toast.makeText(this, "Đây không phải là một tứ giác đặc biệt", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(MainActivity.this, "Vui lòng nhập đúng định dạng x,y Và không được để trống các tọa độ", Toast.LENGTH_SHORT).show();
        }
    }


    public void onClickTinhDienTich(View v) {
        /*Lấy ra point từ các editText*/
        Point point1 = splitData(edPoint1.getText().toString());
        Point point2 = splitData(edPoint2.getText().toString());
        Point point3 = splitData(edPoint3.getText().toString());
        Point point4 = splitData(edPoint4.getText().toString());

        /*TÍnh độ dài 4 cạnh*/
        if (point1 != null && point2 != null && point3 != null && point4 != null) {
            double canhA = TuGiacAbstract.tinhDoDaiCanhKhiBietToaDo2Diem(point1, point2);
            double canhB = TuGiacAbstract.tinhDoDaiCanhKhiBietToaDo2Diem(point2, point3);
            double canhC = TuGiacAbstract.tinhDoDaiCanhKhiBietToaDo2Diem(point3, point4);
            double canhD = TuGiacAbstract.tinhDoDaiCanhKhiBietToaDo2Diem(point4, point1);
            /*Nếu các cạnh thỏa mãn một hình chữ nhật thì vào đoạn if này*/
            if (canhA == canhC && canhB == canhD && canhB != canhC) {
                /*Khởi tạo một đối tượng HinhChuNhatChild.
                * Vì kế thừa class TuGiacAbstract nên có thể khai báo là :
                 * C1: TuGiacAbstract hcn = new HinhChuNhatChild(canhA, canhB, canhC, canhD);
                 * C2 : HinhChuNhatChild hcn = new HinhChuNhatChild(canhA, canhB, canhC, canhD);*/
                TuGiacAbstract hcn = new HinhChuNhatChild(canhA, canhB, canhC, canhD);
                hcn.tinhDienTich();
                hcn.hienThiDienTich(this);
            /* Còn nếu các cạnh thỏa mãn một hình vuông thì vào đoạn if này*/
            } else if (canhA == canhB && canhB == canhC) {
                TuGiacAbstract hv = new HinhVuongChild(canhA, canhB, canhC, canhD);
                hv.tinhDienTich();          //Gọi phương thức tinhDienTich()
                hv.hienThiDienTich(this);  //Gọi phương thức hienThi (đã được implement interface)
            }
            /*Nếu các cạnh không thỏa mãn hình vuông hoặc hình chữ nhật thì vào đoạn else này*/
            else {
                Toast.makeText(this, "Đây không phải là một tứ giác đặc biệt", Toast.LENGTH_SHORT).show();
            }
        }
        //Còn nếu một trong các 4 point bị null nghĩa là k tách được tung độ , hoành độ ra từ Edit Text nên nghĩa là dữ liệu nhập vào sai..
        else {
            Toast.makeText(MainActivity.this, "Vui lòng nhập đúng định dạng x,y Và không được để trống các tọa độ", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onClick(View view) {
        rl_flash_screen.setVisibility(View.GONE);//Khi click vào màn hình Flash screen thì cho nó ẩn đi
        edPoint1.setInputType(InputType.TYPE_CLASS_TEXT);//Cho bàn phím hiện lên khi click vào edittext point 1
        edPoint1.requestFocus(); //Focus vào edittext 1
        InputMethodManager mgr = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE); //Lấy service liên quan đến bàn phím
        mgr.showSoftInput(edPoint1, InputMethodManager.SHOW_FORCED);//Bắt bàn phím hiển thị lên
    }
}