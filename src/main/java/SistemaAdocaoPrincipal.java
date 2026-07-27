import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import exceptions.*;

public class SistemaAdocaoPrincipal extends JFrame {

    // ---------- Paleta de cores (só Swing/AWT, sem bibliotecas externas) ----------
    private static final Color COR_PRIMARIA   = new Color(0x2E7D32); // verde, remete a "adoção/cuidado"
    private static final Color COR_PRIMARIA_ESCURA = new Color(0x1B5E20);
    private static final Color COR_FUNDO      = new Color(0xF4F6F5);
    private static final Color COR_CARD       = Color.WHITE;
    private static final Color COR_TEXTO      = new Color(0x2B2B2B);
    private static final Color COR_TEXTO_SUAVE = new Color(0x6B6B6B);
    private static final Color COR_BORDA      = new Color(0xDDE3E1);

    private static final Font FONTE_TITULO   = new Font("Segoe UI", Font.BOLD, 22);
    private static final Font FONTE_SUBTITULO = new Font("Segoe UI", Font.PLAIN, 13);
    private static final Font FONTE_BOTAO    = new Font("Segoe UI", Font.BOLD, 13);
    private static final Font FONTE_CARD_TITULO = new Font("Segoe UI", Font.BOLD, 14);

    private ControllerAdocao controller;

    public SistemaAdocaoPrincipal() {

        controller = new ControllerAdocao();

        UIManager.put("OptionPane.messageFont", new Font("Segoe UI", Font.PLAIN, 13));
        UIManager.put("OptionPane.buttonFont", FONTE_BOTAO);
        UIManager.put("Button.font", FONTE_BOTAO);

        setTitle("Sistema de Adoção de Animais");
        setSize(820, 600);
        setMinimumSize(new Dimension(700, 500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(COR_FUNDO);
        setLayout(new BorderLayout());

        setJMenuBar(criarBarraDeMenu());
        add(criarCabecalho(), BorderLayout.NORTH);
        add(criarPainelInicial(), BorderLayout.CENTER);

        setVisible(true);
    }

    // ============================================================
    // BARRA DE MENU
    // ============================================================

    /**
     * Monta a barra de menu do sistema, com um menu para cada área
     * (Animais, Pessoas, Adoções, Dados), reaproveitando as mesmas
     * ações usadas pelos botões dos cards.
     */
    private JMenuBar criarBarraDeMenu() {

        JMenuBar barraDeMenu = new JMenuBar();

        JMenu menuAnimais = new JMenu("Animais");
        menuAnimais.add(criarItemDeMenu("Cadastrar animal", e -> acaoCadastrarAnimal()));
        menuAnimais.add(criarItemDeMenu("Pesquisar animal", e -> acaoPesquisarAnimal()));
        menuAnimais.add(criarItemDeMenu("Listar animais disponíveis", e -> acaoListarAnimais()));
        menuAnimais.add(criarItemDeMenu("Remover animal", e -> acaoApagarAnimal()));

        JMenu menuPessoas = new JMenu("Pessoas");
        menuPessoas.add(criarItemDeMenu("Cadastrar pessoa", e -> acaoCadastrarPessoa()));
        menuPessoas.add(criarItemDeMenu("Pesquisar pessoa", e -> acaoPesquisarPessoa()));
        menuPessoas.add(criarItemDeMenu("Listar pessoas", e -> acaoListarPessoas()));
        menuPessoas.add(criarItemDeMenu("Remover pessoa", e -> acaoApagarPessoa()));

        JMenu menuAdocoes = new JMenu("Adoções");
        menuAdocoes.add(criarItemDeMenu("Realizar adoção", e -> acaoRealizarAdocao()));
        menuAdocoes.add(criarItemDeMenu("Pesquisar adoção", e -> acaoPesquisarAdocao()));
        menuAdocoes.add(criarItemDeMenu("Listar adoções", e -> acaoListarAdocoes()));
        menuAdocoes.add(criarItemDeMenu("Remover adoção", e -> acaoApagarAdocao()));

        JMenu menuDados = new JMenu("Dados");
        menuDados.add(criarItemDeMenu("Salvar dados", e -> acaoSalvarDados()));
        menuDados.add(criarItemDeMenu("Carregar dados", e -> acaoCarregarDados()));
        menuDados.addSeparator();
        menuDados.add(criarItemDeMenu("Sair", e -> acaoSair()));

        barraDeMenu.add(menuAnimais);
        barraDeMenu.add(menuPessoas);
        barraDeMenu.add(menuAdocoes);
        barraDeMenu.add(menuDados);

        return barraDeMenu;
    }

    private JMenuItem criarItemDeMenu(String rotulo, ActionListener acao) {
        JMenuItem item = new JMenuItem(rotulo);
        item.addActionListener(acao);
        return item;
    }

    // ============================================================
    // CABEÇALHO (título à esquerda, ação de sair à direita)
    // ============================================================

    private JPanel criarCabecalho() {

        JPanel cabecalho = new JPanel(new BorderLayout());
        cabecalho.setBackground(COR_PRIMARIA);
        cabecalho.setBorder(new EmptyBorder(18, 24, 18, 24));

        JLabel titulo = new JLabel("Sistema de Adoção de Animais");
        titulo.setFont(FONTE_TITULO);
        titulo.setForeground(Color.WHITE);

        JLabel subtitulo = new JLabel("Cadastre animais, pessoas e realize adoções");
        subtitulo.setFont(FONTE_SUBTITULO);
        subtitulo.setForeground(new Color(255, 255, 255, 200));

        JPanel textos = new JPanel();
        textos.setOpaque(false);
        textos.setLayout(new BoxLayout(textos, BoxLayout.Y_AXIS));
        titulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        subtitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        textos.add(titulo);
        textos.add(Box.createVerticalStrut(4));
        textos.add(subtitulo);

        BotaoArredondado botaoSair = new BotaoArredondado("Sair", new Color(255, 255, 255, 40), Color.WHITE, new Color(255, 255, 255, 70));
        botaoSair.addActionListener(e -> acaoSair());

        JPanel painelSair = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        painelSair.setOpaque(false);
        painelSair.add(botaoSair);

        cabecalho.add(textos, BorderLayout.WEST);
        cabecalho.add(painelSair, BorderLayout.EAST);
        return cabecalho;
    }

    // ============================================================
    // PAINEL INICIAL (atalhos organizados em "cards")
    // ============================================================

    private JPanel criarPainelInicial() {

        JPanel painel = new JPanel(new GridLayout(2, 2, 20, 20));
        painel.setBackground(COR_FUNDO);
        painel.setBorder(new EmptyBorder(24, 24, 24, 24));

        painel.add(criarCard("Animais",
                "Cadastre, pesquise, liste ou remova animais.",
                new String[]{"Cadastrar", "Pesquisar", "Listar", "Remover"},
                new ActionListener[]{
                        e -> acaoCadastrarAnimal(),
                        e -> acaoPesquisarAnimal(),
                        e -> acaoListarAnimais(),
                        e -> acaoApagarAnimal()
                }));

        painel.add(criarCard("Pessoas",
                "Cadastre, pesquise, liste ou remova adotantes.",
                new String[]{"Cadastrar", "Pesquisar", "Listar", "Remover"},
                new ActionListener[]{
                        e -> acaoCadastrarPessoa(),
                        e -> acaoPesquisarPessoa(),
                        e -> acaoListarPessoas(),
                        e -> acaoApagarPessoa()
                }));

        painel.add(criarCard("Adoções",
                "Realize, pesquise, liste ou remova adoções.",
                new String[]{"Realizar", "Pesquisar", "Listar", "Remover"},
                new ActionListener[]{
                        e -> acaoRealizarAdocao(),
                        e -> acaoPesquisarAdocao(),
                        e -> acaoListarAdocoes(),
                        e -> acaoApagarAdocao()
                }));

        painel.add(criarCard("Dados",
                "Salve ou carregue os dados do sistema.",
                new String[]{"Salvar dados", "Carregar dados"},
                new ActionListener[]{
                        e -> acaoSalvarDados(),
                        e -> acaoCarregarDados()
                }));

        return painel;
    }

    /** Cria um "card": título, descrição e uma linha de botões estilizados. */
    private JPanel criarCard(String titulo, String descricao,
                             String[] rotulosBotoes, ActionListener[] acoes) {

        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(COR_CARD);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COR_BORDA, 1, true),
                new EmptyBorder(18, 20, 18, 20)
        ));

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(FONTE_CARD_TITULO);
        lblTitulo.setForeground(COR_TEXTO);
        lblTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblDescricao = new JLabel(descricao);
        lblDescricao.setFont(FONTE_SUBTITULO);
        lblDescricao.setForeground(COR_TEXTO_SUAVE);
        lblDescricao.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        botoes.setOpaque(false);
        botoes.setAlignmentX(Component.LEFT_ALIGNMENT);

        for (int i = 0; i < rotulosBotoes.length; i++) {
            BotaoArredondado botao = new BotaoArredondado(rotulosBotoes[i], COR_PRIMARIA, Color.WHITE, COR_PRIMARIA_ESCURA);
            botao.addActionListener(acoes[i]);
            botoes.add(botao);
        }

        card.add(lblTitulo);
        card.add(Box.createVerticalStrut(4));
        card.add(lblDescricao);
        card.add(Box.createVerticalStrut(14));
        card.add(botoes);

        return card;
    }

    // ============================================================
    // BOTÃO ARREDONDADO (Graphics2D puro, cores configuráveis)
    // ============================================================

    private static class BotaoArredondado extends JButton {

        private final Color corNormal;
        private final Color corHover;

        BotaoArredondado(String texto, Color corFundo, Color corTexto, Color corFundoHover) {
            super(texto);
            this.corNormal = corFundo;
            this.corHover = corFundoHover;
            setFont(FONTE_BOTAO);
            setForeground(corTexto);
            setBackground(corFundo);
            setFocusPainted(false);
            setBorderPainted(false);
            setContentAreaFilled(false);
            setOpaque(false);
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            setBorder(new EmptyBorder(8, 16, 8, 16));

            addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent e) {
                    setBackground(corHover);
                }
                public void mouseExited(java.awt.event.MouseEvent e) {
                    setBackground(corNormal);
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
            g2.dispose();
            super.paintComponent(g);
        }
    }

    // ============================================================
    // AÇÕES (usadas tanto pelos botões dos cards quanto pelo menu)
    // ============================================================

    private void acaoSair() {
        int resposta = JOptionPane.showConfirmDialog(
                this, "Deseja realmente sair?", "Sair", JOptionPane.YES_NO_OPTION);
        if (resposta == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    private void acaoCadastrarAnimal() {
        try {

            String nome = JOptionPane.showInputDialog(this, "Nome do animal:");
            if (nome == null) return;

            String codigoStr = JOptionPane.showInputDialog(this, "Código:");
            if (codigoStr == null) return;
            int codigo = Integer.parseInt(codigoStr);

            String raca = JOptionPane.showInputDialog(this, "Raça:");
            if (raca == null) return;

            String nascimento = JOptionPane.showInputDialog(this, "Nascimento:");
            if (nascimento == null) return;

            Animal.Especie especie = (Animal.Especie) JOptionPane.showInputDialog(
                    this, "Espécie:", "Escolha",
                    JOptionPane.QUESTION_MESSAGE, null,
                    Animal.Especie.values(), Animal.Especie.CACHORRO);
            if (especie == null) return;

            Animal.Sexo sexo = (Animal.Sexo) JOptionPane.showInputDialog(
                    this, "Sexo:", "Escolha",
                    JOptionPane.QUESTION_MESSAGE, null,
                    Animal.Sexo.values(), Animal.Sexo.MACHO);
            if (sexo == null) return;

            int respostaCastrado = JOptionPane.showConfirmDialog(
                    this, "É castrado?", "Castrado",
                    JOptionPane.YES_NO_OPTION);
            if (respostaCastrado != JOptionPane.YES_OPTION && respostaCastrado != JOptionPane.NO_OPTION) return; // fechou o diálogo
            boolean castrado = respostaCastrado == JOptionPane.YES_OPTION;

            Animal animal = new Animal(nome, sexo, codigo, especie, nascimento, castrado, raca, false);

            controller.cadastrarAnimal(animal);

            JOptionPane.showMessageDialog(this, "Animal cadastrado!");

        } catch (AnimalCodigoJaExisteException erro) {
            JOptionPane.showMessageDialog(this, erro.getMessage());

        } catch (NumberFormatException erro) {
            JOptionPane.showMessageDialog(this, "Código inválido!");

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar animal");
        }
    }

    private void acaoCadastrarPessoa() {
        try {
            String cpf = JOptionPane.showInputDialog(this, "CPF:");
            if (cpf == null) return;

            String nome = JOptionPane.showInputDialog(this, "Nome:");
            if (nome == null) return;

            String endereco = JOptionPane.showInputDialog(this, "Endereço:");
            if (endereco == null) return;

            String telefone = JOptionPane.showInputDialog(this, "Telefone:");
            if (telefone == null) return;

            Pessoa pessoa = new Pessoa(cpf, nome, endereco, telefone);

            controller.cadastrarPessoa(pessoa);

            JOptionPane.showMessageDialog(this, "Pessoa cadastrada!");

        } catch (PessoaJaCadastradaException erro) {
            JOptionPane.showMessageDialog(this, erro.getMessage());
        }
    }

    private void acaoPesquisarAnimal() {
        try {
            int codigo = Integer.parseInt(JOptionPane.showInputDialog(this, "Código do animal:"));

            Animal animal = controller.pesquisarAnimal(codigo);

            JOptionPane.showMessageDialog(this, animal.toString());

        } catch (AnimalNaoEncontradoException erro) {
            JOptionPane.showMessageDialog(this, erro.getMessage());

        } catch (NumberFormatException erro) {
            JOptionPane.showMessageDialog(this, "Código inválido");
        }
    }

    private void acaoPesquisarPessoa() {
        try {
            String cpf = JOptionPane.showInputDialog(this, "Digite o CPF da pessoa:");

            Pessoa pessoa = controller.pesquisarPessoa(cpf);

            JOptionPane.showMessageDialog(this, pessoa.toString());

        } catch (PessoaNaoExisteException erro) {
            JOptionPane.showMessageDialog(this, erro.getMessage());
        }
    }

    private void acaoPesquisarAdocao() {
        try {
            int codigo = Integer.parseInt(JOptionPane.showInputDialog(this, "Código da adoção:"));

            Adocao resultadoAdocao = controller.pesquisarAdocao(codigo);

            JOptionPane.showMessageDialog(this, resultadoAdocao.toString());

        } catch (AdocaoNaoEncontradaException erro) {
            JOptionPane.showMessageDialog(this, erro.getMessage());

        } catch (NumberFormatException erro) {
            JOptionPane.showMessageDialog(this, "Digite somente números!");
        }
    }

    private void acaoListarAnimais() {
        JOptionPane.showMessageDialog(this, controller.listarAnimais());
    }

    private void acaoListarPessoas() {
        JOptionPane.showMessageDialog(this, controller.listarPessoas());
    }

    private void acaoListarAdocoes() {
        JOptionPane.showMessageDialog(this, controller.listarAdocoes());
    }

    private void acaoApagarAnimal() {
        try {
            int codigo = Integer.parseInt(JOptionPane.showInputDialog(this, "Código do animal:"));
            controller.removerAnimal(codigo);
            JOptionPane.showMessageDialog(this, "Animal removido!");

        } catch (NumberFormatException erro) {
            JOptionPane.showMessageDialog(this, "Código inválido!");
        }
    }

    private void acaoApagarPessoa() {
        String cpf = JOptionPane.showInputDialog(this, "CPF da pessoa:");
        controller.removerPessoa(cpf);
        JOptionPane.showMessageDialog(this, "Pessoa removida!");
    }

    private void acaoApagarAdocao() {
        try {
            int codigo = Integer.parseInt(JOptionPane.showInputDialog(this, "Código da adoção:"));
            controller.removerAdocao(codigo);
            JOptionPane.showMessageDialog(this, "Adoção removida!");

        } catch (NumberFormatException erro) {
            JOptionPane.showMessageDialog(this, "Código inválido!");
        }
    }

    private void acaoRealizarAdocao() {
        try {
            int codigoAnimal = Integer.parseInt(JOptionPane.showInputDialog(this, "Código do animal:"));

            Animal animal = controller.pesquisarAnimal(codigoAnimal);

            String cpf = JOptionPane.showInputDialog(this, "CPF do adotante:");
            Pessoa pessoa = controller.pesquisarPessoa(cpf);

            String data = JOptionPane.showInputDialog(this, "Data da adoção:");
            int codigoAdocao = Integer.parseInt(JOptionPane.showInputDialog(this, "Código da adoção:"));

            controller.realizarAdocao(pessoa, animal, data, codigoAdocao);

            JOptionPane.showMessageDialog(this, "Adoção realizada com sucesso!");

        } catch (AnimalJaAdotadoException erro) {
            JOptionPane.showMessageDialog(this, erro.getMessage());

        } catch (AdocaoCodigoJaExisteException erro) {
            JOptionPane.showMessageDialog(this, erro.getMessage());

        } catch (AnimalNaoEncontradoException erro) {
            JOptionPane.showMessageDialog(this, erro.getMessage());

        } catch (PessoaNaoExisteException erro) {
            JOptionPane.showMessageDialog(this, erro.getMessage());

        } catch (NumberFormatException erro) {
            JOptionPane.showMessageDialog(this, "Digite números nos códigos!");

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(this, "Erro: " + erro.getMessage());
        }
    }

    private void acaoSalvarDados() {
        controller.salvarDados();
    }

    private void acaoCarregarDados() {
        controller.carregarDados();
        JOptionPane.showMessageDialog(this, "Dados carregados com sucesso!");
    }
}