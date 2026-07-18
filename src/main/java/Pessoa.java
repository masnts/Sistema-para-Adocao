import java.util.Objects;

public class Pessoa {
    private String cpf;
    private String nome;
    private String endereco;
    private String telefone;

    public Pessoa(String cpf, String nome, String endereco, String telefone){
        this.cpf=cpf;
        this.nome=nome;
        this.endereco=endereco;
        this.telefone=telefone;
    }

    public Pessoa(){
        this("sem cpf","sem nome","sem endereço","sem telefone");
    }

    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString(){
        return "br.com.Adocao.Pessoa: "+this.nome+ ", do cpf: "+this.cpf+
                "\nEndereço: "+this.endereco+
                "\nTelefone: "+this.telefone;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa pessoa = (Pessoa) o;
        return Objects.equals(cpf, pessoa.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }
}
