package br.com.magna.service.boardModel;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.magna.service.view.InterfaceUsuario;

public class Tabuleiro {

	private static final Logger logger = LoggerFactory.getLogger(InterfaceUsuario.class);
	private static Integer linha;
	private static Integer coluna;
	private static Integer contador;
	private static Integer tamanho;
	static StringBuilder palavra = new StringBuilder();
	static Random caracter = new Random();

	public static Map<Integer, Map<Integer, Character>> criarMatriz(int size) {
		Map<Integer, Map<Integer, Character>> matriz = new HashMap<>();

		for (contador = 1; contador <= size; contador++) {
			matriz.put(contador, new HashMap<>());
		}
		return matriz;
	}

	public static void colocarPalavras(Map<Integer, Map<Integer, Character>> matriz, String[] palavras) {
		
		
	 tamanho = matriz.size();

		for (String palavra : palavras) {

			Boolean colocada = false;

			while (!colocada) {

				Integer inicioLinha = caracter.nextInt(tamanho) + 1;
				Integer inicioColuna = caracter.nextInt(tamanho) + 1;
				Boolean horizontal = caracter.nextBoolean();

				if (horizontal) {
					if (inicioColuna + palavra.length() - 1 <= tamanho) {
						Boolean podeColocar = true;

						for (coluna = inicioColuna; coluna < inicioColuna + palavra.length(); coluna++) {
							if (matriz.get(inicioLinha).containsKey(coluna)
									&& matriz.get(inicioLinha).get(coluna) != palavra.charAt(coluna - inicioColuna)) {
								podeColocar = false;
								break;
							}
						}
						if (podeColocar) {

							for (coluna = inicioColuna; coluna < inicioColuna + palavra.length(); coluna++) {
								matriz.get(inicioLinha).put(coluna, palavra.charAt(coluna - inicioColuna));
							}
							colocada = true;
						}
					}
				} else {

					if (inicioLinha + palavra.length() - 1 <= tamanho) {
						Boolean podeColocar = true;

						for (linha = inicioLinha; linha < inicioLinha + palavra.length(); linha++) {
							if (matriz.get(linha).containsKey(inicioColuna)
									&& matriz.get(linha).get(inicioColuna) != palavra.charAt(linha - inicioLinha)) {
								podeColocar = false;
								break;
							}
						}
						if (podeColocar) {
							for (linha = inicioLinha; linha < inicioLinha + palavra.length(); linha++) {
								matriz.get(linha).put(inicioColuna, palavra.charAt(linha - inicioLinha));
							}
							colocada = true;
						}
					}
				}
			}
		}
	}

	public static void preencherEspacosVazios(Map<Integer, Map<Integer, Character>> matriz) {
		
		tamanho = matriz.size();

		for (linha = 1; linha <= tamanho; linha++) {
			for (coluna = 1; coluna <= tamanho; coluna++) {
				if (!matriz.get(linha).containsKey(coluna)) {
					matriz.get(linha).put(coluna, caracteresAleatorios(caracter));
				}
			}
		}
	}

	public static char caracteresAleatorios(Random aleatorio) {
		return (char) (aleatorio.nextInt(26) + 'A');
	}

	public static void exibirMatriz(Map<Integer, Map<Integer, Character>> matriz) {

		tamanho = matriz.size();

		System.out.print("  ");

		for (int numeros = 1; numeros <= tamanho; numeros++) {
			System.out.printf("%02d ", numeros);
		}
		logger.info("");

		for (int letras = 1; letras <= tamanho; letras++) {
			System.out.print((char) ('A' + letras - 1) + "  ");

			for (int caracteres = 1; caracteres <= tamanho; caracteres++) {
				System.out
					  .print(matriz.get(letras)
					  .getOrDefault(caracteres, caracteresAleatorios(new Random())) + "  ");
			}
			logger.info("");
		}
	}
}
