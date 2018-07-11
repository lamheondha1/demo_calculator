package com.example.lam.demo;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CalculatorFragment extends Fragment implements View.OnClickListener {
    private TextView mTxt;
    private TextView mTxtKq;
    private Button mBtn0;
    private Button mBtn1;
    private Button mBtn2;
    private Button mBtn3;
    private Button mBtn4;
    private Button mBtn5;
    private Button mBtn6;
    private Button mBtn7;
    private Button mBtn8;
    private Button mBtn9;

    private Button mBtnCong;
    private Button mBtnTru;
    private Button mBtnNhan;
    private Button mBtnChia;

    private Button mBtnPt;
    private Button mBtnTp;
    private Button mBtnBang;
    private Button mBtnAc;
    private Button mBtnAm;
    private Button mBtnDel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calculator, container, false);
        mTxt = view.findViewById(R.id.text);
        mTxtKq = view.findViewById(R.id.kq);
        mBtn0 = view.findViewById(R.id.btn_0);
        mBtn1 = view.findViewById(R.id.btn_1);
        mBtn2 = view.findViewById(R.id.btn_2);
        mBtn3 = view.findViewById(R.id.btn_3);
        mBtn4 = view.findViewById(R.id.btn_4);
        mBtn5 = view.findViewById(R.id.btn_5);
        mBtn6 = view.findViewById(R.id.btn_6);
        mBtn7 = view.findViewById(R.id.btn_7);
        mBtn8 = view.findViewById(R.id.btn_8);
        mBtn9 = view.findViewById(R.id.btn_9);
        mBtnTp = view.findViewById(R.id.btn_tp);
        mBtnAm= view.findViewById(R.id.btn_am);
        mBtnCong = view.findViewById(R.id.btn_cong);
        mBtnTru = view.findViewById(R.id.btn_tru);
        mBtnNhan = view.findViewById(R.id.btn_nhan);
        mBtnChia= view.findViewById(R.id.btn_chia);
        mBtnPt = view.findViewById(R.id.btn_pt);
        mBtnAc = view.findViewById(R.id.btn_ac);
        mBtnDel = view.findViewById(R.id.btn_del);
        mBtnBang = view.findViewById(R.id.btn_bang);
        setButton();
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.back_menu:
                mTxtKq.setText("0");
                mTxt.setText(" ");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setButton() {
        mBtn0.setOnClickListener(this);
        mBtn1.setOnClickListener(this);
        mBtn2.setOnClickListener(this);
        mBtn3.setOnClickListener(this);
        mBtn4.setOnClickListener(this);
        mBtn5.setOnClickListener(this);
        mBtn6.setOnClickListener(this);
        mBtn7.setOnClickListener(this);
        mBtn8.setOnClickListener(this);
        mBtn9.setOnClickListener(this);
        mBtnPt.setOnClickListener(this);
        mBtnAm.setOnClickListener(this);
        mBtnTp.setOnClickListener(this);
        mBtnCong.setOnClickListener(this);
        mBtnTru.setOnClickListener(this);
        mBtnNhan.setOnClickListener(this);
        mBtnChia.setOnClickListener(this);
        mBtnBang.setOnClickListener(this);
        mBtnAc.setOnClickListener(this);
        mBtnDel.setOnClickListener(this);
    }
    public String delete(String num){
        int lenght = num.length();
        return num.substring(0,lenght-1);

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_0 :
                mTxt.append("0");
                break;
            case R.id.btn_1 :
                mTxt.append("1");
                break;
            case R.id.btn_2 :
                mTxt.append("2");
                break;
            case R.id.btn_3 :
                mTxt.append("3");
                break;
            case R.id.btn_4 :
                mTxt.append("4");
                break;
            case R.id.btn_5 :
                mTxt.append("5");
                break;
            case R.id.btn_6 :
                mTxt.append("6");
                break;
            case R.id.btn_7 :
                mTxt.append("7");
                break;
            case R.id.btn_8 :
                mTxt.append("8");
                break;
            case R.id.btn_9 :
                mTxt.append("9");
                break;
            case R.id.btn_am :
                mTxt.append("-");
                break;
            case R.id.btn_pt:
                mTxt.append("%");
                break;
            case R.id.btn_tp:
                mTxt.append(".");
                break;
            case R.id.btn_cong :
                mTxt.append("+");
                break;
            case R.id.btn_tru:
                mTxt.append("-");
                break;
            case R.id.btn_nhan:
                mTxt.append("*");
                break;
            case R.id.btn_chia:
                mTxt.append("/");
                break;
            case R.id.btn_bang:
                Equal();
                break;
            case R.id.btn_ac:
                mTxt.setText("");
                mTxtKq.setText("0");
                break;
            case R.id.btn_del:
                String del = delete(mTxt.getText().toString());
                mTxt.setText(del);
                break;
        }

    }
    ArrayList<String> pheptinh;
    ArrayList<Double> so;
    public void Equal(){
        DecimalFormat df = new DecimalFormat("###.#######");
        double result = 0;
        addPhepTinh(mTxt.getText().toString());
        addSo(mTxt.getText().toString());
        // Thuật toán tính toán biểu thức
        if (pheptinh.size() >= so.size() || pheptinh.size() < 0) {
            mTxtKq.setText("Error");
        } else {
            for (int i = 0; i < (so.size() - 1); i++) {
                switch (pheptinh.get(i)) {
                    case "+":
                        if (i == 0) {
                            result = so.get(i) + so.get(i + 1);
                        } else {
                            result = result + so.get(i + 1);
                        }
                        break;
                    case "-":
                        if (i == 0) {
                            result = so.get(i) - so.get(i + 1);
                        } else {
                            result = result - so.get(i + 1);
                        }
                        break;
                    case "*":
                        if (i == 0) {
                            result = so.get(i) * so.get(i + 1);
                        } else {
                            result = result * so.get(i + 1);
                        }
                        break;
                    case "/":
                        if (i == 0) {
                            result = so.get(i) / so.get(i + 1);
                        } else {
                            result = result / so.get(i + 1);
                        }
                        break;
                    case "%":
                        if (i == 0) {
                            result = (so.get(i) / so.get(i + 1))*100;
                        } else {
                            result = result / so.get(i + 1);
                        }
                        break;
                    default:
                        break;
                }
            }
            mTxtKq.setText(df.format(result) + "");
        }

    }


    public void addPhepTinh(String a){
        pheptinh = new ArrayList<>();
        char[] cArray = a.toCharArray();
        for (char aCArray : cArray) {
            switch (aCArray) {
                case '+':
                    pheptinh.add(aCArray + "");
                    break;
                case '-':
                    pheptinh.add(aCArray + "");
                    break;
                case '*':
                    pheptinh.add(aCArray + "");
                    break;
                case '/':
                    pheptinh.add(aCArray + "");
                    break;
                case '%':
                    pheptinh.add(aCArray + "");
                    break;
                default:
                    break;
            }
        }
    }
    public void addSo(String stringInput) {
        so = new ArrayList<>();
        Pattern regex = Pattern.compile("(\\d+(?:\\.\\d+)?)");
        Matcher matcher = regex.matcher(stringInput);
        while(matcher.find()){
            so.add(Double.valueOf(matcher.group(1)));
        }
    }
}
