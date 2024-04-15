package util;

import java.util.Scanner;

public class ScanUtil   {
	// 스캐너를 손쉽게 사용할 수 있는 static 메서드를 가지고있음
	static Scanner sc = new Scanner(System.in);
	
	public static String nextLine(String print) {
		System.out.print(print);
		return nextLine();
	}
	
	private static String nextLine() {
		return sc.nextLine();
	}
	
	public static int nextInt(String print) {	// 스캐너 사용 시 반드시 안내문구를 입력해라. 개발자가 안내문구를 적게끔 막아둠
		System.out.print(print);
		return nextInt();
	}
	
	private static int nextInt() {	// 숫자 외의 문자가 입력되었을 때 오류 체크
		while(true) {
			try {
				int result = Integer.parseInt(sc.nextLine());
				return result;
			}catch (NumberFormatException e) {
				System.out.println("잘못 입력!!");
			}
		}
	}
}
