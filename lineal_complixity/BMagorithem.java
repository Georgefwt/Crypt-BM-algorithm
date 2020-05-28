package lineal_complixity;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

class polynomial{
	int coefficient[];
	int index;
	public polynomial() {
		coefficient=new int[10000];
		index=0;
	}
	@Override
	protected Object clone() throws CloneNotSupportedException {
		polynomial obclone=new polynomial();
		obclone.coefficient=coefficient.clone();
		obclone.index=index;
		return obclone;
	}
}

public class BMagorithem {
	public static void main(String[] args) throws CloneNotSupportedException, IOException {
		File inpuFile=new File("input.txt");
		FileInputStream inputStream=new FileInputStream(inpuFile);
		BufferedInputStream binputStream=new BufferedInputStream(inputStream);
		polynomial cd=new polynomial();
		cd.coefficient[0]=1;
		polynomial bd=new polynomial();
		bd.coefficient[0]=1;
		polynomial td=new polynomial();
		cd.coefficient[0]=1;bd.coefficient[0]=1;
		int L=0,m=0,j=0;
		int d;
		int specialflag=0;//only function when input length over 10000
		
		ArrayList<Integer> S=new ArrayList<Integer>();
		int lastin=(int)binputStream.read();
		S.add(lastin-48);

		while (lastin!=-1) {
			d=S.get(j-specialflag);
			for (int i = 1; i <=L; i++) {
				d=d^(cd.coefficient[i]*S.get(j-i-specialflag));
			}
			m++;
			if (d!=0) {
				td=(polynomial) cd.clone();
				for (int i = 0; i <=bd.index ; i++) {
					try {
						cd.coefficient[m+i]=cd.coefficient[m+i]^bd.coefficient[i];
					} catch (Exception e) {
						System.out.print("Linear Complexity over 10000");
						return;
					}
				}
				cd.index=Math.max(cd.index, bd.index+m);
				if (L<=j/2) {
					L=j+1-L;
					bd=(polynomial) td.clone();
					m=0;
				}
			}
			j++;
			lastin=(int)binputStream.read();
			S.add(lastin-48);
			if (j>10000) {
				S.remove(0);//save storage space
				specialflag++;
			}
		}
		binputStream.close();
		System.out.println(L);//print results
		System.out.println(cd.index);
		for (int i = 0; i < cd.index+1; i++) {
			System.out.print(cd.coefficient[i]+"D^"+i+'+');
		}
	}
}
