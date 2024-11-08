public class Interpretador {

    VerificaArquivo verificaArquivo = VerificaArquivo.getInstancia();
    private VerificaComando verificaComando;

    // Construtor padrão
    public Interpretador()
    {
        this.verificaComando = new VerificaComando(this);
        inicializarRegistradores();
    }

    // Construtor com injeção de dependência para VerificaComando
    public Interpretador(VerificaComando verificaComando)
    {
        this.verificaComando = verificaComando;
        // Inicializando os registradores com valor 0
        inicializarRegistradores();
    }

    // Metodo auxiliar para inicializar os registradores
    private void inicializarRegistradores()
    {
        a = b = c = d = e = f = g = h = i = j = k = l = m = n = o = p = q = r = s = t = u = v = w = x = y = z = 0;
    }

    // Registradores de 'a' até 'z'
    private int a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z;

    // Copia o valor de y para o registrador x
    public void executeMov(String x, String y)
    {
        if (isNumero(y))
        {
            setValorRegistrador(x.charAt(0), Integer.parseInt(y));
        } else
        {
            setValorRegistrador(x.charAt(0), getValorRegistrador(y.charAt(0)));
        }
    }

    // Incrementa o valor de x em 1
    public void executeInc(char x)
    {
        setValorRegistrador(x, getValorRegistrador(x) + 1);
    }

    // Decrementa o valor de x em 1
    public void executeDec(char x)
    {
        setValorRegistrador(x, getValorRegistrador(x) - 1);
    }

    // Adiciona o valor de y ao registrador x
    public void executeAdd(char x, char y)
    {
        setValorRegistrador(x, getValorRegistrador(x) + getValorRegistrador(y));
    }

    // Subtrai o valor de y do registrador x
    public void executeSub(char x, String y)
    {
        if(isNumero(y))
        {
            setValorRegistrador(x, getValorRegistrador(x) - Integer.parseInt(y));
        }else
        {
            setValorRegistrador(x, getValorRegistrador(x) - getValorRegistrador(y.charAt(0)));
        }
    }

    // Multiplica o valor de x pelo valor de y
    public void executeMul(char x, char y)
    {
        setValorRegistrador(x, getValorRegistrador(x) * getValorRegistrador(y));
    }

    // Divide o valor de x pelo valor de y
    public void executeDiv(char x, char y)
    {
        if (getValorRegistrador(y) != 0)
        {
            setValorRegistrador(x, getValorRegistrador(x) / getValorRegistrador(y));
        } else
        {
            System.out.println("Erro: Divisão por zero!");
        }
    }

    // Se o valor no registrador x for diferente de zero, pula para a linha de número y
    public void executeJnz(char x, int linhaDestino, int linhaAtual)
    {
        int registrador = getValorRegistrador(x);

        // Se o valor do registrador for zero, não entra no laço
        LinkedList lista = verificaArquivo.getLista();
        if (registrador != 0)
        {
            String comando;
            lista.resetaIterator();

            // Processa os comandos até a linha atual
            while ((comando = lista.getNextCommand()) != null)
            {
                String[] partes = comando.split(" ");
                int linhaComando = Integer.parseInt(partes[0]);

                // Se a linha do comando estiver entre a linha de destino e a linha atual
                if (linhaComando >= linhaDestino && linhaComando <= linhaAtual){
                    // Chama a função de interpretação do comando
                    verificaComando.toInterpretacao(comando);
                }
            }
        }else // Caso for zero executa os comandos restantes na lista
        {
            String comando;
            lista.resetaIterator();

            // Processa os comandos para frente da linha atual
            while ((comando = lista.getNextCommand()) != null)
            {
                String[] partes = comando.split(" ");
                int linhaComando = Integer.parseInt(partes[0]);

                // Se a linha do comando estiver na frente da linha atual
                if (linhaComando > linhaAtual){
                    verificaComando.toInterpretacao(comando);
                }
            }
        }
    }

    // Exibe o valor do registrador x em tela
    public void executeOut(char x) {
        System.out.println(getValorRegistrador(x));
    }

    // Métodos auxiliares privados
    private int getValorRegistrador(char reg) {
        switch (reg) {
            case 'a': return a;
            case 'b': return b;
            case 'c': return c;
            case 'd': return d;
            case 'e': return e;
            case 'f': return f;
            case 'g': return g;
            case 'h': return h;
            case 'i': return i;
            case 'j': return j;
            case 'k': return k;
            case 'l': return l;
            case 'm': return m;
            case 'n': return n;
            case 'o': return o;
            case 'p': return p;
            case 'q': return q;
            case 'r': return r;
            case 's': return s;
            case 't': return t;
            case 'u': return u;
            case 'v': return v;
            case 'w': return w;
            case 'x': return x;
            case 'y': return y;
            case 'z': return z;
            default: return 0; // Caso inválido
        }
    }

    private void setValorRegistrador(char reg, int valor) {
        switch (reg) {
            case 'a': a = valor; break;
            case 'b': b = valor; break;
            case 'c': c = valor; break;
            case 'd': d = valor; break;
            case 'e': e = valor; break;
            case 'f': f = valor; break;
            case 'g': g = valor; break;
            case 'h': h = valor; break;
            case 'i': i = valor; break;
            case 'j': j = valor; break;
            case 'k': k = valor; break;
            case 'l': l = valor; break;
            case 'm': m = valor; break;
            case 'n': n = valor; break;
            case 'o': o = valor; break;
            case 'p': p = valor; break;
            case 'q': q = valor; break;
            case 'r': r = valor; break;
            case 's': s = valor; break;
            case 't': t = valor; break;
            case 'u': u = valor; break;
            case 'v': v = valor; break;
            case 'w': w = valor; break;
            case 'x': x = valor; break;
            case 'y': y = valor; break;
            case 'z': z = valor; break;
        }
    }

    // Verifica se o valor é um número
    private boolean isNumero(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}