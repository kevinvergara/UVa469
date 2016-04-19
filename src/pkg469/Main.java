
package pkg469;

import java.util.*;

class Main { 
  
  private static String linea;
  private static char[][] cuadro = new char[150][];
  private static int TC, R, C, fila, columna;

  private static int dr[] = {1,1,0,-1,-1,-1, 0, 1}; // S,SE,E,NE,N,NW,W,SW
  private static int dc[] = {0,1,1, 1, 0,-1,-1,-1}; // vecinos

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    
    TC = sc.nextInt(); sc.nextLine();
    sc.nextLine(); // linea ficticia

    while (TC-- > 0) {
      R = 0;
      while (true) {
        cuadro[R] = sc.nextLine().toCharArray();
        if (cuadro[R][0] != 'L' && cuadro[R][0] != 'W'){ // inicio de la consulta
          break;
        }
        R++;
      }
      C = cuadro[0].length;


      linea = new String(cuadro[R]);
      while (true) {
        StringTokenizer st = new StringTokenizer(linea);
        
        fila = Integer.parseInt(st.nextToken()); 
        fila--; // índice comienza a partir de 0
        
        columna = Integer.parseInt(st.nextToken()); 
        columna--;
        
        System.out.println(floodfill(fila, columna, 'W', '.')); // cambiar el agua 'W' por .
        floodfill(fila, columna, '.', 'W'); // restaurar la siguiente consulta
        
        if (sc.hasNext()){ 
            linea = sc.nextLine();
        }else{
            break;
        } // último caso de prueba
        
        if (linea.length() == 0){
            break;
        } // siguiente caso de prueba
      }

      if (TC > 0){
        System.out.println();
      }
    }
  }
  
  private static int floodfill(int r, int c, char c1, char c2) {
    if (r < 0 || r >= R || c < 0 || c >= C){ 
        return 0;
    } // fuera de rango
    if (cuadro[r][c] != c1){ 
        return 0;
    } // sólo queremos c1
    
    cuadro[r][c] = c2; // important step to avoid cycling!
    int ans = 1; // coloring c1 -> c2, add 1 to answer
    
    for (int d = 0; d < 8; d++){ // recurse to neighbors
      ans += floodfill(r + dr[d], c + dc[d], c1, c2);
    }
    
    return ans;
  }
}