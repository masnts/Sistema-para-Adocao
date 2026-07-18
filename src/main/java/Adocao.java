import java.util.Objects;

public class Adocao {
    private Animal animalAdotado;
    private Pessoa adotante;
    private String dataDeAdocao;
    private int codAdocao;

    public Adocao(Pessoa pessoa, Animal animal, String data, int codigo){
        this.animalAdotado= animal;
        this.adotante= pessoa;
        this.dataDeAdocao= data;
        this.codAdocao= codigo;
    }

    public Animal getAnimalAdotado() {
        return animalAdotado;
    }
    public void setAnimalAdotado(Animal animalAdotado) {
        this.animalAdotado = animalAdotado;
    }

    public Pessoa getAdotante() {
        return adotante;
    }
    public void setAdotante(Pessoa adotante) {
        this.adotante = adotante;
    }

    public String getDataDeAdocao() {
        return dataDeAdocao;
    }
    public void setDataDeAdocao(String dataDeAdocao) {
        this.dataDeAdocao = dataDeAdocao;
    }

    public int getCodAdocao() {
        return codAdocao;
    }
    public void setCodAdocao(int codAdocao) {
        this.codAdocao = codAdocao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Adocao adocao = (Adocao) o;
        return Objects.equals(codAdocao, adocao.codAdocao);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(codAdocao);
    }

    public String toString(){
        return"Adoção: "+
                "\nAnimal adotado: "+this.animalAdotado.getNome()+
                "\nPessoa que adotou: "+this.adotante.getNome()+", do cpf: "+this.adotante.getCpf()+
                "\nData de adoção: "+this.dataDeAdocao;

    }
}
