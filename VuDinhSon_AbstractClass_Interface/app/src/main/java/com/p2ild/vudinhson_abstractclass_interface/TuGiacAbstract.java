package com.p2ild.vudinhson_abstractclass_interface;

import android.content.Context;
import android.graphics.Point;
import android.widget.Toast;

/**
 * Created by duypi on 9/14/2016.
 * Abstract class là một class như kiểu một người bố,
 * những người con mà ông bố này đẻ ra sẽ thừa kế được các đặc tính của ông bố này
 */
public abstract class TuGiacAbstract implements TuGiacInterface{
    /* Ông bố tứ giác này có những ĐẶC ĐIỂM(là các biến)
     * và
     * HÀNH ĐỘNG(method) như sau : */

    //2 dòng này là khai báo những đặc điểm(biến) mà ông bố có :
    protected Point pointA,pointB,pointC,pointD; // Có 4 đỉnh
    protected double chuVi,dienTich; // có chu vi và diện tích
    protected double canhA,canhB,canhC,canhD;

    public TuGiacAbstract(double canhA,double canhB,double canhC,double canhD) {
        this.canhA = canhA;
        this.canhB = canhB;
        this.canhC = canhC;
        this.canhD = canhD;
    }

    //Ông bố có 3 hành động (method) là tính chu vi, tính diện tích, và hiển thị kết quả :
    //Có những ông con kế thừa ông bố ở hành đông này nhưng lại không kế thừa những hành động khác (nghĩa là tùy người code có cho thằng con thực thi method tính chu vi hay tính diện tích không)
    //Còn những method bắt buộc phải có thì thêm từ khóa Abstract vào trước void thì thằng con sẽ bắt buộc phải kế thừa dù muốn hay không (Ở đây phương thức in ra e để abstract)
    public abstract void tinhChuVi();

    public abstract void tinhDienTich();

    public static double tinhDoDaiCanhKhiBietToaDo2Diem(Point diemThu1,Point diemThu2){
        double khoangCachGiua2Diem
                = Math.sqrt(
                Math.pow(diemThu1.x - diemThu2.x,2)
                +Math.pow(diemThu1.y - diemThu2.y,2)
        );
        return khoangCachGiua2Diem;
    }
}
