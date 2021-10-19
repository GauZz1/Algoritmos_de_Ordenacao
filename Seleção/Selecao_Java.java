import java.util.Date;

class Series
{

	private String nome, formato, duracao, pais, idioma, emissora, transmissao;
	private int temporada, episodios;

	//Construtor vazio
	public Series()
	{
		this.nome = this.formato = this.duracao = this.pais = this.idioma = this.emissora = this.transmissao = "";
		this.episodios = temporada = 0;

	}
	public Series(String nome, String formato, String duracao, String pais, String idioma, String emissora, String transmissao, int episodios)
	{
		this.nome = nome;
		this.formato = formato;
		this.duracao = duracao;
		this.pais = pais;
		this.idioma = idioma;
		this.emissora = emissora;
		this.transmissao = transmissao;
		this.temporada = temporada;
		this.episodios = episodios;

	}
	//Getters e Setters
	public void setNome(String nome){
		this.nome = nome;
	}

	public String getNome(){
		return this.nome;
	}

	public void setFormato(String formato){
		this.formato = formato;
	}

	public String getFormato(){
		return this.formato;
	}

	public void setDuracao(String duracao){
		this.duracao = duracao;
	}

	public String getDuracao(){
		return this.duracao;
	}

	public void setPais(String pais){
		this.pais = pais;
	}

	public String getPais(){
		return this.pais;
	}

	public void setIdioma(String idioma){
		this.idioma = idioma;
	}

	public String getIdioma(){
		return this.idioma;
	}

	public void setEmissora(String emissora){
		this.emissora = emissora;
	}

	public String getEmissora(){
		return this.emissora;
	}

	public void setTransmissao(String transmissao){
		this.transmissao = transmissao;
	}

	public String getTransmissao(){
		return this.transmissao;
	}

	public void setTemporada(int temporada){
		this.temporada = temporada;
	}

	public int getTemporada(){
		return this.temporada;
	}

	public void setEpisodios(int episodios){
		this.episodios = episodios;
	}

	public int getEpisodios(){
		return this.episodios;
	}
	
	//Fim Getters e Setter

	public void ler(String[] palavras)
	{

		//leitura do itens de cada serie e .trim() para retirar espaços em branco
		//do inicio e fim da string
		this.nome = palavras[0].trim();
		this.formato = palavras[1].trim();
		this.duracao = palavras[2].trim();
		this.pais = palavras[3].trim();
		this.idioma = palavras[4].trim();
		this.emissora = palavras[5].trim();
		this.transmissao = palavras[6].trim();
		this.temporada = Integer.parseInt(palavras[7]);
		this.episodios = Integer.parseInt(palavras[8]);
	}

	public void imprimir()
	{
		System.out.print(nome + " ");
		System.out.print(formato+ " ");
		System.out.print(duracao + " ");
		System.out.print(pais + " ");
		System.out.print(idioma + " ");
		System.out.print(emissora + " ");
		System.out.print(transmissao + " ");
		System.out.print(temporada + " ");
		System.out.println(episodios);
	}
	public Series(Series clone)
	{
		this.nome = clone.nome;
		this.formato = clone.formato;
		this.duracao = clone.duracao;
		this.pais = clone.pais;
		this.idioma = clone.idioma;
		this.emissora = clone.emissora;
		this.transmissao = clone.transmissao;
		this.temporada = clone.temporada;
		this.episodios = clone.episodios;

	}

}

class Selecao_Java{

	private static int comp = 0;
	private static int mov = 0;

	public static boolean isFim(String palavra)
	{
		boolean ehFim = false;
		if(palavra.length() == 3 && (palavra.charAt(0)=='F') && (palavra.charAt(1)=='I') && (palavra.charAt(2)=='M')) ehFim = true;

		return ehFim;
	}

	public static String[] tratamento(String nomeArquivo)
	{

		//Essa função faz o tratamento de cada linha do arquivo para padronizar
		//retirando as tags e tudo que não for utilizado dos arquivos
		String palavras[] = new String[9];

		Arq.openRead(nomeArquivo, "UTF-8");

		palavras[0] = nomeArquivo.replaceAll("/tmp/series/", "");
		palavras[0] = palavras[0].replaceAll(".html", "");
		palavras[0] = palavras[0].replaceAll("_", " ");


		while(!Arq.readLine().contains("Formato"));
		palavras[1] = (Arq.readLine()).replaceAll("<.*?>", "");

		while(!Arq.readLine().contains("Duração"));
		palavras[2] = (Arq.readLine()).replaceAll("<.*?>", "");

		while(!Arq.readLine().contains("País de origem"));
		palavras[3] = (Arq.readLine()).replaceAll("<.*?>", "");
		palavras[3] = palavras[3].replaceAll("&.*?;", "");

		while(!Arq.readLine().contains("Idioma original"));
		palavras[4] = (Arq.readLine()).replaceAll("<.*?>", "");

		while(!Arq.readLine().contains("Emissora de televisão original"));
		palavras[5] = (Arq.readLine()).replaceAll("<.*?>", "");
		if(palavras[5].charAt(0) == ' ') palavras[5] = palavras[5].substring(1, palavras[5].length());

		while(!Arq.readLine().contains("Transmissão original"));
		palavras[6] = (Arq.readLine()).replaceAll("<.*?>", "");
		palavras[6] = palavras[6].replaceAll("&.*?;", "");

		while(!Arq.readLine().contains("N.º de temporadas"));
		palavras[7] = (Arq.readLine()).replaceAll("<.*?>", "");
		if(palavras[7].contains(" ")) palavras[7] = palavras[7].substring(0, (palavras[7].indexOf(" ")));

		while(!Arq.readLine().contains("N.º de episódios"));
		palavras[8] = (Arq.readLine()).replaceAll("<.*?>", "");
		if(palavras[8].contains(" ")) palavras[8] = palavras[8].substring(0, (palavras[8].indexOf(" ")));

		Arq.close();

		return palavras;
	}

	public static void ordenarSelecao(Series series[], int n)
	{
		Series tmp;

		for (int i = 0; i < (n - 1); i++) {
			int menor = i;
			for (int j = (i + 1); j < n; j++){
				comp++;
				if (series[menor].getPais().compareTo(series[j].getPais()) < 0){ /*nessa parte do código são comparados os países das series nas posições menor e j e se o valor do compareTo for menor que zero o elemento da posição j passa a ocupar a posição menor*/
				   menor = j;
				}else if (series[menor].getPais().compareTo(series[j].getPais()) == 0 && series[menor].getNome().compareTo(series[j].getNome()) < 0){ /*nessa parte do código são comparados os países das series nas posições menor e j e se o valor do compareTo for igual a zero as series são ordenadas pelo nome*/
				    menor = j;
				    comp+=2;
				}
			}
			//troca da posição dos elementos
			tmp = series[menor];
			series[menor] = series[i];
			series[i] = tmp;
			mov+=3;
      		}
	}

	public static void main(String[] args) throws Exception
	{
		Series itens[] = new Series[500];
		int i = 0;
		String nomeArquivo = "/tmp/series/"; //caso esteja executando mudar essa caminho de series em todos os lugares q tiver
		String conteudo;

		String nome = MyIO.readLine();

		//Lê item por item do arquivo de entrada "pub.in"
		while(isFim(nome) == false){

			nomeArquivo += nome; //concatena no do arquivo com o nome
			
			Series item = new Series();
			item.ler(tratamento(nomeArquivo));
			itens[i] = item;
			i++;

			nomeArquivo = "/tmp/series/";
			nome = MyIO.readLine();

		}

		// marca o inicio da contagem do tempo de execução da ordenação
		long tInicio = new Date().getTime();

		ordenarSelecao(itens, i);

		//marca do fim da contagem do tempo
		long tFim = new Date().getTime();
		//calcula o tempo total de execução;
		long tTotal = (tFim-tInicio);

		for(int j = i-1; j >= 0; j--){

			itens[j].imprimir();

		}
		//Cria um arquivo contendo minha matricula, num de comparações num de movimentações
		// e o tempo de execução do algoritmo de ordenação

		String conteudoArqLog = ("691468\t" + comp + " comparações\t" + mov + " movimentações\t" + tTotal + "ms");
		Arq.openWriteClose("691468_selecao.txt", "UTF-8", conteudoArqLog);	

	}
}
