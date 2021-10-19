class algoritmoSelecao {

  public static void ordenarSelecao(Series series[], int n)
    {
      Series tmp;

      for (int i = 0; i < (n - 1); i++) {
        int menor = i;
        for (int j = (i + 1); j < n; j++){
          comp++;
          if (series[menor].getPais().compareTo(series[j].getPais()) < 0) menor = j /*nessa parte do código são comparados os países das series nas posições menor e j e se o valor do compareTo for menor que zero o elemento da posição j passa a ocupar a posição menor*/
        }
        //troca da posição dos elementos
        tmp = series[menor];
        series[menor] = series[i];
        series[i] = tmp;
        mov+=3;
        }
    }
    
}
