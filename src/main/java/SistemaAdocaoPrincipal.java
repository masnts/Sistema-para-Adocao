import javax.swing.*;

public class SistemaAdocaoPrincipal extends JFrame {

    private ControllerAdocao controller;

    public SistemaAdocaoPrincipal() {

        controller = new ControllerAdocao();

        setTitle("Sistema de Adoção de Animais");
        setSize(800,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        criarMenu();

        setVisible(true);
    }


    private void criarMenu(){

        JMenuBar menuBar = new JMenuBar();


        JMenu cadastro = new JMenu("Cadastrar");
        JMenu pesquisa = new JMenu("Pesquisar");
        JMenu apagar = new JMenu("Apagar");
        JMenu adocao = new JMenu("Adoção");
        JMenu salvar = new JMenu("Dados");
        JMenu sistema = new JMenu("Sistema");


        JMenuItem cadastrarAnimal =
                new JMenuItem("Cadastrar Animal");

        JMenuItem cadastrarPessoa =
                new JMenuItem("Cadastrar Pessoa");


        JMenuItem pesquisarAnimal =
                new JMenuItem("Pesquisar Animal");

        JMenuItem pesquisarPessoa =
                new JMenuItem("Pesquisar Pessoa");

        JMenuItem pesquisarAdocao =
                new JMenuItem("Pesquisar Adoção");


        JMenuItem listarAnimais =
                new JMenuItem("Listar Animais");

        JMenuItem listarPessoas =
                new JMenuItem("Listar Pessoas");

        JMenuItem listarAdocoes =
                new JMenuItem("Listar Adoções");


        JMenuItem apagarAnimal =
                new JMenuItem("Apagar Animal");

        JMenuItem apagarPessoa =
                new JMenuItem("Apagar Pessoa");

        JMenuItem apagarAdocao =
                new JMenuItem("Apagar Adoção");


        JMenuItem realizarAdocao =
                new JMenuItem("Realizar Adoção");


        JMenuItem salvarDados =
                new JMenuItem("Salvar Dados");


        JMenuItem carregarDados =
                new JMenuItem("Carregar Dados");


        JMenuItem sair =
                new JMenuItem("Sair");



        // adicionando menus

        cadastro.add(cadastrarAnimal);
        cadastro.add(cadastrarPessoa);


        pesquisa.add(pesquisarAnimal);
        pesquisa.add(pesquisarPessoa);
        pesquisa.add(pesquisarAdocao);

        pesquisa.addSeparator();

        pesquisa.add(listarAnimais);
        pesquisa.add(listarPessoas);
        pesquisa.add(listarAdocoes);



        apagar.add(apagarAnimal);
        apagar.add(apagarPessoa);
        apagar.add(apagarAdocao);



        adocao.add(realizarAdocao);



        salvar.add(salvarDados);
        salvar.add(carregarDados);


        sistema.add(sair);



        menuBar.add(cadastro);
        menuBar.add(pesquisa);
        menuBar.add(apagar);
        menuBar.add(adocao);
        menuBar.add(salvar);
        menuBar.add(sistema);


        setJMenuBar(menuBar);



        // ============================
        // CADASTRAR ANIMAL
        // ============================


        cadastrarAnimal.addActionListener(e -> {

            try {


                String nome = JOptionPane.showInputDialog(
                        this,
                        "Nome do animal:"
                );


                int codigo = Integer.parseInt(
                        JOptionPane.showInputDialog(
                                this,
                                "Código:"
                        )
                );


                String raca = JOptionPane.showInputDialog(
                        this,
                        "Raça:"
                );


                String nascimento = JOptionPane.showInputDialog(
                        this,
                        "Nascimento:"
                );



                Animal.Especie especie =
                        (Animal.Especie) JOptionPane.showInputDialog(
                                this,
                                "Espécie:",
                                "Escolha",
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                Animal.Especie.values(),
                                Animal.Especie.CACHORRO
                        );



                Animal.Sexo sexo =
                        (Animal.Sexo) JOptionPane.showInputDialog(
                                this,
                                "Sexo:",
                                "Escolha",
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                Animal.Sexo.values(),
                                Animal.Sexo.MACHO
                        );



                boolean castrado =
                        JOptionPane.showConfirmDialog(
                                this,
                                "É castrado?",
                                "Castrado",
                                JOptionPane.YES_NO_OPTION
                        ) == JOptionPane.YES_OPTION;



                Animal animal = new Animal(
                        nome,
                        sexo,
                        codigo,
                        especie,
                        nascimento,
                        castrado,
                        raca,
                        false
                );


                controller.cadastrarAnimal(animal);



                JOptionPane.showMessageDialog(
                        this,
                        "Animal cadastrado!"
                );



            }catch(Exception erro){

                JOptionPane.showMessageDialog(
                        this,
                        "Erro ao cadastrar animal"
                );

            }


        });



        // ============================
        // CADASTRAR PESSOA
        // ============================


        cadastrarPessoa.addActionListener(e -> {


            String cpf = JOptionPane.showInputDialog(
                    this,
                    "CPF:"
            );


            String nome = JOptionPane.showInputDialog(
                    this,
                    "Nome:"
            );


            String endereco = JOptionPane.showInputDialog(
                    this,
                    "Endereço:"
            );


            String telefone = JOptionPane.showInputDialog(
                    this,
                    "Telefone:"
            );



            Pessoa pessoa = new Pessoa(
                    cpf,
                    nome,
                    endereco,
                    telefone
            );


            controller.cadastrarPessoa(pessoa);



            JOptionPane.showMessageDialog(
                    this,
                    "Pessoa cadastrada!"
            );


        });



        // ============================
        // PESQUISAR ANIMAL
        // ============================


        pesquisarAnimal.addActionListener(e -> {


            try {


                int codigo = Integer.parseInt(
                        JOptionPane.showInputDialog(
                                this,
                                "Código do animal:"
                        )
                );


                Animal animal =
                        controller.pesquisarAnimal(codigo);



                if(animal != null){

                    JOptionPane.showMessageDialog(
                            this,
                            animal.toString()
                    );

                }else{

                    JOptionPane.showMessageDialog(
                            this,
                            "Animal não encontrado"
                    );

                }


            }catch(Exception erro){

                JOptionPane.showMessageDialog(
                        this,
                        "Código inválido"
                );

            }


        });


        // ============================
        // PESQUISAR PESSOA
        // ============================

        pesquisarPessoa.addActionListener(e -> {


            try {

                String cpf = JOptionPane.showInputDialog(
                        this,
                        "Digite o CPF da pessoa:"
                );


                Pessoa pessoa =
                        controller.pesquisarPessoa(cpf);



                JOptionPane.showMessageDialog(
                        this,
                        pessoa.toString()
                );



            } catch(PessoaNaoExisteException erro){


                JOptionPane.showMessageDialog(
                        this,
                        erro.getMessage()
                );


            }


        });




        // ============================
        // PESQUISAR ADOÇÃO
        // ============================


        pesquisarAdocao.addActionListener(e -> {


            try {


                int codigo = Integer.parseInt(
                        JOptionPane.showInputDialog(
                                this,
                                "Código da adoção:"
                        )
                );


                Adocao resultadoAdocao =
                        controller.pesquisarAdocao(codigo);



                if(adocao != null){
                    JOptionPane.showMessageDialog(
                            this,
                            adocao.toString()
                    );


                }else{


                    JOptionPane.showMessageDialog(
                            this,
                            "Adoção não encontrada!"
                    );


                }



            }catch(NumberFormatException erro){


                JOptionPane.showMessageDialog(
                        this,
                        "Digite somente números!"
                );


            }


        });






        // ============================
        // LISTAR ANIMAIS
        // ============================


        listarAnimais.addActionListener(e -> {


            JOptionPane.showMessageDialog(
                    this,
                    controller.listarAnimais()
            );


        });




        // ============================
        // LISTAR PESSOAS
        // ============================


        listarPessoas.addActionListener(e -> {


            JOptionPane.showMessageDialog(
                    this,
                    controller.listarPessoas()
            );


        });




        // ============================
        // LISTAR ADOÇÕES
        // ============================


        listarAdocoes.addActionListener(e -> {


            JOptionPane.showMessageDialog(
                    this,
                    controller.listarAdocoes()
            );


        });







        // ============================
        // APAGAR ANIMAL
        // ============================


        apagarAnimal.addActionListener(e -> {


            try {


                int codigo = Integer.parseInt(
                        JOptionPane.showInputDialog(
                                this,
                                "Código do animal:"
                        )
                );


                controller.removerAnimal(codigo);



                JOptionPane.showMessageDialog(
                        this,
                        "Animal removido!"
                );



            }catch(NumberFormatException erro){


                JOptionPane.showMessageDialog(
                        this,
                        "Código inválido!"
                );


            }


        });






        // ============================
        // APAGAR PESSOA
        // ============================


        apagarPessoa.addActionListener(e -> {


            String cpf = JOptionPane.showInputDialog(
                    this,
                    "CPF da pessoa:"
            );


            controller.removerPessoa(cpf);



            JOptionPane.showMessageDialog(
                    this,
                    "Pessoa removida!"
            );


        });






        // ============================
        // APAGAR ADOÇÃO
        // ============================


        apagarAdocao.addActionListener(e -> {


            try {


                int codigo = Integer.parseInt(
                        JOptionPane.showInputDialog(
                                this,
                                "Código da adoção:"
                        )
                );


                controller.removerAdocao(codigo);



                JOptionPane.showMessageDialog(
                        this,
                        "Adoção removida!"
                );



            }catch(NumberFormatException erro){


                JOptionPane.showMessageDialog(
                        this,
                        "Código inválido!"
                );


            }


        });






        // ============================
        // REALIZAR ADOÇÃO
        // ============================


        realizarAdocao.addActionListener(e -> {


            try {


                int codigoAnimal = Integer.parseInt(
                        JOptionPane.showInputDialog(
                                this,
                                "Código do animal:"
                        )
                );



                Animal animal =
                        controller.pesquisarAnimal(codigoAnimal);



                if(animal == null){


                    JOptionPane.showMessageDialog(
                            this,
                            "Animal não encontrado!"
                    );

                    return;

                }




                String cpf = JOptionPane.showInputDialog(
                        this,
                        "CPF do adotante:"
                );



                Pessoa pessoa =
                        controller.pesquisarPessoa(cpf);





                String data = JOptionPane.showInputDialog(
                        this,
                        "Data da adoção:"
                );




                int codigoAdocao = Integer.parseInt(
                        JOptionPane.showInputDialog(
                                this,
                                "Código da adoção:"
                        )
                );




                controller.realizarAdocao(
                        pessoa,
                        animal,
                        data,
                        codigoAdocao
                );



                JOptionPane.showMessageDialog(
                        this,
                        "Adoção realizada com sucesso!"
                );




            }catch(AnimalJaAdotadoException erro){


                JOptionPane.showMessageDialog(
                        this,
                        erro.getMessage()
                );


            }catch(PessoaNaoExisteException erro){


                JOptionPane.showMessageDialog(
                        this,
                        erro.getMessage()
                );


            }catch(NumberFormatException erro){


                JOptionPane.showMessageDialog(
                        this,
                        "Digite números nos códigos!"
                );


            }catch(Exception erro){


                JOptionPane.showMessageDialog(
                        this,
                        "Erro: " + erro.getMessage()
                );


            }


        });


        // ============================
        // SALVAR DADOS
        // ============================


        salvarDados.addActionListener(e -> {


            controller.salvarDados();



            JOptionPane.showMessageDialog(
                    this,
                    "Dados salvos com sucesso!"
            );


        });





        // ============================
        // CARREGAR DADOS
        // ============================


        carregarDados.addActionListener(e -> {


            controller.carregarDados();



            JOptionPane.showMessageDialog(
                    this,
                    "Dados carregados com sucesso!"
            );


        });






        // ============================
        // SAIR DO SISTEMA
        // ============================


        sair.addActionListener(e -> {


            int resposta =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Deseja realmente sair?",
                            "Sair",
                            JOptionPane.YES_NO_OPTION
                    );



            if(resposta == JOptionPane.YES_OPTION){


                System.exit(0);


            }


        });


    }

}