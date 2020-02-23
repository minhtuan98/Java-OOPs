/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baitaplon2;

/**
 *
 * @author MINH TUAN
 */
public class SinhVien {

        int MSSV;
        String HoTen;
        String NgaySinh;
        float DiemTrungBinhTichLuy;
        int SoTinChi;
        
        public SinhVien(int MSSV,String HoTen,String NgaySinh,float DiemTrungBinhTichLuy,int SoTinChi)
        {
          //  System.out.print("PP " + MSSV);
            this.MSSV = MSSV;
            this.HoTen = HoTen;
            this.NgaySinh = NgaySinh;
            this.DiemTrungBinhTichLuy = DiemTrungBinhTichLuy;
            this.SoTinChi = SoTinChi;
        }
    }
