package baitaplon2;

//import baitaplon.BaiTapLon.SinhVien;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.util.Random;

/**
 *
 * @author MINH TUAN
 */
public class BaiTapLon {

    public Node root;
    public static final String FILENAME = "C:\\Users\\MINH TUAN\\Desktop\\Finish Java2\\BaiTapLon2\\src\\baitaplon2\\FileDB.txt";
    public class Node {

        SinhVien key;
        Node left, right,parent;
        int size;
        int height;

        public Node(SinhVien key, int size, int height) {
            this.key = key;
            this.size = size;
            this.height = height;
        }
        public Node(){}
    }

    public BaiTapLon() {
    }
      public int SoSanh(int a,int b)
        {
            if(a < b)
                return 0;
            else if(a > b)
                return 1;
            else return 2;
        }
    
   public Node put(Node x, SinhVien key) {
        if (x == null) {
            return new Node(key, 1, 0); //Create Empty Tree
        }
          int temp = SoSanh(key.MSSV,x.key.MSSV);
        if (temp == 0) {
            x.left = put(x.left, key);
        }
        else if (temp == 1) {
            x.right = put(x.right, key);
        } else {
            x.key = key;
        }
        x.size = 1 + size(x.left) + size(x.right);
        x.height = 1 + Math.max(height(x.left), height(x.right));
        return balance(x);
    }

    public void put(SinhVien key) {
        root = put(root, key);
    }
    //Tao cay
    public void CreateTree(String strKey)
    {
        root = null;
        try{
        BufferedReader br = new BufferedReader(new FileReader(strKey));        
        int S = parseInt(br.readLine());
        
        for(int i = 0;i< S;i++)
        {
            String line = br.readLine();
            String[] strTree = line.split("\t"); 
            SinhVien sv = new SinhVien(Integer.parseInt(strTree[0]),strTree[1],strTree[2],Float.parseFloat(strTree[3]),Integer.parseInt(strTree[4]));
            put(sv);
        }
        br.close();
        }
        catch(IOException e){};
    }

    //Get Size
    public int size(Node x) {
        if (x == null) {
            return 0;
        } else {
            return x.size;
        }
    }

    public int size() {
        return size(root);
    }

    //Get Height
    public int height(Node x) {
        if (x == null) {
            return -1;
        } else {
            return x.height;
        }
    }

    public int height() {
        return height(root);
    }

    //o	Giải thuật duyệt cây theo: LNR, LRN, NLR, RNL, NRL, RLN. Hiển thị đầy đủ thông tin của một node (bao gồm: mã số sinh viên, họ tên, ngày sinh, điểm trung bình, số tín chỉ tích luỹ).
    //LNR
    public void PrintInfo(SinhVien key)
    {       
            System.out.println("Ma ID: " + key.MSSV);
            System.out.println("Ho Ten: " + key.HoTen);
            System.out.println("Ngay Sinh: " + key.NgaySinh);
            System.out.println("DiemTBTL: " + key.DiemTrungBinhTichLuy);  
            System.out.println("So TC: " + key.SoTinChi);                 
            System.out.println("--------------------------------------");
    }
    public void LNR(Node x) {
        
        if (x != null) {
          LNR(x.left);
          PrintInfo(x.key);
          LNR(x.right);
        }
    }

    public void LNR() {
        LNR(root);
    }

    // LRN
    public void LRN(Node x) {
        if (x != null) {
            LRN(x.left);
            LRN(x.right);
            PrintInfo(x.key);
        }
    }

    public void LRN() {
        LRN(root);
    }

    //NLR
    public void NLR(Node x) {
        if (x != null) {
            PrintInfo(x.key);
            NLR(x.left);
            NLR(x.right);
        }
        
    }

    public void NLR() {
        NLR(root);
    }

    //RNL
    public void RNL(Node x) {
        if (x != null) {
            RNL(x.right);
            PrintInfo(x.key);
            RNL(x.left);
        }
    }

    public void RNL() {
        RNL(root);
    }

    //   NRL
    public void NRL(Node x) {
        if (x != null) {
            PrintInfo(x.key);
            NRL(x.right);
            NRL(x.left);
        }
    }

    public void NRL() {
        NRL(root);
    }

    //RLN
    public void RLN(Node x) {
        if(x != null)
        {
        RLN(x.right);
        RLN(x.left);
        PrintInfo(x.key);
        }
    }
    public void RLN()
    {
        RLN(root);
    }

    //o	Giải thuật tìm kiếm: theo mã số sinh viên, theo họ tên, theo ngày sinh, theo điểm trung bình, theo số tín chỉ tích luỹ, tìm phần tử lớn nhất – nhỏ nhất của cây
    //Find by MSSV
    public void FindByID(Node x, SinhVien key) {
        if (x == null) {
            System.out.println("Khong co SV nao co ID la : " + key.MSSV);
        }
        else
        {
        int temp = SoSanh(key.MSSV,x.key.MSSV);
        if (temp == 0) {
             FindByID(x.left, key);
        } else if (temp == 1) {
             FindByID(x.right, key);
        } else 
            PrintInfo(x.key);
    }
    }
    public void FindByID(SinhVien key)
    {
     //   System.out.println(key.MSSV);
         FindByID(root, key);
    }
    
    public void FindByName(Node x, SinhVien key) {
        if(x != null){
        int temp = x.key.HoTen.compareTo(key.HoTen);
             if(temp < 0)
              FindByName(x.left, key);
             else if(temp > 0)
              FindByName(x.right,key);
             else
              PrintInfo(x.key);
        }
        else
              System.out.println("Khong co SV nao co ten la : " + key.HoTen);
             
          
        }
    
    public void FindByName(SinhVien key)
    {
        FindByName(root,key);
    }
    
    //Find by DateOfBirth
    public void FindByDateOfBirth(Node x, SinhVien key) {
        if (x != null) {
                int temp = x.key.NgaySinh.compareTo(key.NgaySinh);
                 if (temp < 0) 
                FindByDateOfBirth(x.left, key);
                 else if(temp > 0)
                FindByDateOfBirth(x.right, key);
                else
                PrintInfo(x.key);
        }
        else
            System.out.println("Khong co SV co NgaySinh la : " + key.NgaySinh);
    }
    public void FindByDateOfBirth(SinhVien key)
    {
        FindByDateOfBirth(root,key);
    }

    //Find by DiemTrungBinh
    public void FindByAVGPoint(Node x, SinhVien key) {
        if( x != null)
        {
            float temp = Float.compare(x.key.DiemTrungBinhTichLuy, key.DiemTrungBinhTichLuy);
            if(temp < 0)
                FindByAVGPoint(x.left,key);
            else if(temp > 0)
                FindByAVGPoint(x.right,key);
            else
                PrintInfo(x.key);
        }
        else
            System.out.println("Khong co sinh vien nao co DTB la : " + key.DiemTrungBinhTichLuy);
    }
    public void FindByAVGPoint(SinhVien key)
    {
        FindByAVGPoint(root,key);
    }

    //Find by SoTinChi
    public void FindByNumberOfCredit(Node x, SinhVien key) {
        if (x == null) {
            System.out.println("Khong co SV nao co So Tin Chi la : " + key.SoTinChi);
        }
        else
        {
        int temp = SoSanh(key.SoTinChi,x.key.SoTinChi);
        if (temp == 0) {
             FindByNumberOfCredit(x.left, key);
        } else if (temp == 1) {
             FindByNumberOfCredit(x.right, key);
        } else 
            PrintInfo(x.key);
        }
    }
    public void FindByNumberOfCredit(SinhVien key)
    {
        FindByNumberOfCredit(root,key);
    }

    // Find min
    public Node FindMin(Node x) {
        if (x.left == null) {
            PrintInfo(x.key);
        } else {
             FindMin(x.left);
        }
        return x;
    }

    public Node FindMin() {
         return FindMin(root);
    }

    //Find max
    public Node FindMax(Node x) {
        if (x.right == null) {
            PrintInfo(x.key);
        } else {
             FindMax(x.right);
        }
        return x;
    }

    public Node FindMax() {
         return FindMax(root);
    }
    
    //Delete Node
    private Node DeleteNode(Node x, SinhVien key) {
        if (x == null) return null;
        int cmp = Integer.compare(key.MSSV, x.key.MSSV);
        if      (cmp < 0) x.left  = DeleteNode(x.left,  key);
        else if (cmp > 0) x.right = DeleteNode(x.right, key);
        else { 
            if (x.right == null) return x.left;
            if (x.left  == null) return x.right;
            Node t = x;
            x = FindMin(t.right);
            x.right = DeleteNode(t.right,key);
            x.left = t.left;
        } 
        x.size = 1 + size(x.left) + size(x.right);
        x.height = 1 + Math.max(height(x.left), height(x.right));
        return balance(x);
    } 
    public Node DeleteNode(SinhVien key)
    {
        return DeleteNode(root,key);
    }
 
    //Cay lech phai
    public void CreateRightBranch(int Min,int Max){ //Min = 50 , Max = 70
        for(int i = 85;i < 90;i++)
        {
            Random rand = new Random();
            int value = (int)rand.nextInt(Max - Min) + Min;
            Min = Max;
            Max = Min + 10;
            System.out.println("i = " +i);
            SinhVien sv = new SinhVien(value,"SV" +i,"15-07-2000",6.0f,100);
            put(sv);
        }
    }
    //Cay lech trai
     public void CreateLeftBranch(int Min,int Max){ //Min = 100 , Max = 150
        for(int i = 85;i < 90;i++)
        {
            Random rand = new Random();
            int value = (int)rand.nextInt(Max - Min) + Min;
            Max = Min;
            Min -= 15 ;
            SinhVien sv = new SinhVien(value,"SV" +i,"15-07-2000",6.0f,100);
            put(sv);
        }
    }
     
     //Cap nhat Hoten,NgaySinh,DTB,SoTinChi dua vao ID
     public void UpdateInfo(Node x,SinhVien key,String name,String NgaySinh,float DTB,int STC)
     {
         if(x != null)
         {
              DeleteNode(key);
              SinhVien sv = new SinhVien(key.MSSV,name,NgaySinh,DTB,STC);
              put(sv);
         }
     }
     public void UpdateInfo(SinhVien key,String name,String NgaySinh,float DTB,int STC)
     {
         UpdateInfo(root, key, name, NgaySinh, DTB, STC);
     }
     
    //Ham tu can bang
     private Node balance(Node x) {
        if (balanceFactor(x) < -1) {
            if (balanceFactor(x.right) > 0) {
                x.right = rotateRight(x.right);
            }
            x = rotateLeft(x);
        }
        else if (balanceFactor(x) > 1) {
            if (balanceFactor(x.left) < 0) {
                x.left = rotateLeft(x.left);
            }
            x = rotateRight(x);
        }
        return x;
    }
     private Node rotateRight(Node x) {
        Node y = x.left;
        x.left = y.right;
        y.right = x;
        y.size = x.size;
        x.size = 1 + size(x.left) + size(x.right);
        x.height = 1 + Math.max(height(x.left), height(x.right));
        y.height = 1 + Math.max(height(y.left), height(y.right));
        return y;
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        x.right = y.left;
        y.left = x;
        y.size = x.size;
        x.size = 1 + size(x.left) + size(x.right);
        x.height = 1 + Math.max(height(x.left), height(x.right));
        y.height = 1 + Math.max(height(y.left), height(y.right));
        return y;
    }  

    private int balanceFactor(Node x) {
        return height(x.left) - height(x.right);
    }
    
    public static void main(String[] args)
    {
        /*
        Hoan Thanh :
        1.CreateTree
        2.Them tung tu cac Node
        3.Them danh sach cac Node bang cach doc file
        4.Tim bang ID 
        5.Tim bang Ten
        6.Tim bang Ngáyinh
        7.Tim bang DiemTrungBinh
        8.Tim bang SoTinChi
        9.Duyet LNR, LRN, NLR, RNL, NRL, RLN
        10.Tim phan tu nho nhat
        11.Tim phan tu lon nhat
        12.Tao cay lech trai
        13.Tao cay lech phai
        14.Xoa 1 node
        15.Xoa toan bo node
        16.Cap nhat thong tin Node : Cap nhat ten,ngaysinh,diemTrungBinh,SoTinChi bang ID
        17.Check Balance
        18.Rotate Right
        19.Rotate Left
        */
       
        
        BaiTapLon BTL = new BaiTapLon();
        //Cac cau lenh hoan hanh
        //1.Tao cay bang cach doc file
        //  System.out.println("--------Add student by Reading File---------");
         // BTL.CreateTree(FILENAME);
         // BTL.LNR();
        

        //2.Them vao sinh vien
        //System.out.println("--------Add student---------");
        //SinhVien sv1 = new SinhVien(105,"MinhTuanTran","17-07-1998",4.0f,7760);
        //BTL.put(sv1);
        
        //3.Duyet LNR, LRN, NLR, RNL, NRL, RLN
        //System.out.println("--------Left Node Right---------");
        //  BTL.LNR();
        //System.out.println("--------Left Right Node---------");
        //  BTL.LRN();
        //System.out.println("--------Node Left Right---------");
        //  BTL.NLR();
        //System.out.println("--------Right Node Left---------");
        //  BTL.RNL();
        //System.out.println("--------Node Right Left---------");
        //  BTL.NRL();
        //System.out.println("--------Right Left Node---------");
        //  BTL.RLN();
        
        
          
        //4.Tim bang ID
        //  System.out.println("--------Find By ID---------");
        //  SinhVien sv2 = new SinhVien(101,"TranMinhMinh","17-05-1998",6.0f,90); //Cho du nhap sai cac thong tin con lai cua SinhVien, nhung dung ID thi van tim duoc SV ay voi dung thong tin.
        //  BTL.FindByID(sv2); 

        //5.Tim bang ten
        //  System.out.println("-------Find By Name--------");
        //  SinhVien sv3 = new SinhVien(1103,"TranMinhTuan","10-10-1998",7.0f,10111110); //Cho du nhap sai thong tin con lai cua SinhVien, nhung dung Name thi van tim duoc SV ay voi dung thong tin.
        //  BTL.FindByName(sv3); //Neu du lieu sai thi sao
        
        //6.Tim bang NgaySinh
        //  System.out.println("----Find By DateOfBirth----");
        //  SinhVien sv4 = new SinhVien(101,"MinhTuanTran","15-07-1998",8.0f,50); //Cho du nhap sai thong tin con lai cua SinhVien, nhung dung NgaySinh thi van tim duoc SV ay voi dung thong tin.
        // BTL.FindByDateOfBirth(sv4); //Neu du lieu sai thi sao
       
        //7.Tim bang DiemTrungBinh
        //   System.out.println("----Find By AVGPoint----");
        //   SinhVien sv5 = new SinhVien(106,"MinhTuanTranTest","17-07-19981",5.0f,701); //Cho du nhap sai thong tin con lai cua SinhVien, nhung dung DiemTrungBinh thi van tim duoc SV voi dung thong tin.
        //  BTL.FindByAVGPoint(sv5);
         
        //8.Tim bang SoTinChi
        //   System.out.println("----Find By NumberOfCredit----");
        //   SinhVien sv6 = new SinhVien(107,"MinhTuanAA","11-01-1991",5.0f,90);
        //  BTL.FindByNumberOfCredit(sv6);
        
        //9.Tim phan tu lon nhat
        //   System.out.println("----Find Max of Tree----");
        //   BTL.FindMax();
        
        //10.Tim phan tu nho nhat   
        //   System.out.println("----Find Min of Tree----");
        //   BTL.FindMin();
        
        //11.Delete node
        //   System.out.println("----- Delete Node ------"); 
        //  BTL.LNR();
        //  BTL.DeleteNode(sv1);

        //12.Lech trai
        //   System.out.println("-----Create Left Branch ------");
        // BTL.CreateLeftBranch(100,150);
        // BTL.RNL();
            
        //13.Lech phai
        //   System.out.println("-----Create Right Branch ------");
        // BTL.CreateRightBranch(50,70);
        // BTL.RLN();
           
        //14.Update Info
        //  System.out.println("-----Update Info ------");
        //  SinhVien UpdateSV = new SinhVien(222,"Test Update","17-05-1998",9.0f,90);
        //  BTL.put(UpdateSV);
        //  BTL.UpdateInfo(UpdateSV, "Update Name", "Update DateOfBirth", 210.0f, 110);
        // BTL.RNL();
          
    }
}