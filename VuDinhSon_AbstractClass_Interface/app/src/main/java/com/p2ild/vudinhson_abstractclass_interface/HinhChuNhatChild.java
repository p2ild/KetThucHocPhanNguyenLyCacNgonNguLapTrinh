package com.p2ild.vudinhson_abstractclass_interface;

import android.content.Context;
import android.graphics.Point;
import android.widget.Toast;

/**
 * Created by duypi on 9/14/2016.
 * Ông con có tên hình chữ nhật thực chất là 1 hình tứ giác đặc biệt nên sẽ có các đặc điểm cũng như hành động của ông bố
 * Để cho con kế thừa ông bố ở đây ta sẽ dùng từ khóa Extends
 */
public class HinhChuNhatChild extends TuGiacAbstract {

    /*Đây là phương thức khởi tạo
    * 1 class khi có phương thức này thì đây sẽ là phương thức chạy đầu tiên khi class này được gọi
    * Cú pháp của một phương thức là public <tên class>(){}
    * */
    public HinhChuNhatChild(double canhA, double canhB, double canhC, double canhD) {
        /*Ở class cha(TuGiacAbstract) có phương thức khởi tạo
        * nên khi class này kế thừa nó cũng kế thừa luôn cả phương thức khởi tạo của class cha
        * từ khóa super là gọi phương thức khởi tạo ở class cha vào
        * , nếu không gọi từ khóa này ở đây thì các câu lệnh trong phương thức khởi tạo ở class cha sẽ k được thực thi*/
        super(canhA, canhB, canhC, canhD);
    }

    /*Method tính chu vi
    * Từ khóa Overide vì lớp cha có phương thức này nên phải GHI ĐÈ lại*/
    @Override
    public void tinhChuVi() {
        chuVi = canhA+canhB+canhC+canhD;
    }

    /*Method tính diện tích
    * Từ khóa Overide vì lớp cha có phương thức này nên phải GHI ĐÈ lại*/
    @Override
    public void tinhDienTich() {
        dienTich = (canhA*canhB);

    }

    /*2 Method hiển thị
    * Từ khóa Overide vì class này implement(thực thi) TuGiacInterface và trong interface đó có method này nên phải GHI ĐÈ lại
    * Ở đây vì Class cha là TuGiacAbstract đã implement và lớp con lại extend từ lớp cha nên 2 method này phải implement.
    * (Hoặc cách khác là Class cha k implement Interface nữa mà từng class con implement một, 2 cách này đều như nhau)
    * Không giống như abstract class có method phải ghi đè(nếu có từ khóa abstract) , có method không cần ghi đè
    * tất cả các method trong 1 interface đều mặc định là có từ khóa abstract mặc dù k hiển thị
    * Các class con implement itnterface đó và phải Override tất cả các method đó*/
    @Override
    public void hienThiChuVi(Context context) {
        Toast.makeText(context, "Chu vi hình chữ nhật là : "+chuVi, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hienThiDienTich(Context context) {
        Toast.makeText(context, "Diện tích hình chữ nhật là : "+dienTich, Toast.LENGTH_SHORT).show();
    }
}
