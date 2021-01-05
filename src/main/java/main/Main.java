package main;

public class Main {
    public static void main(String[] args) {

        AlunoDAO alunoDAO = new AlunoDAO();

        // busca todos
        alunoDAO.getAll().forEach(System.out::println);

        // busca por id
        // System.out.println("Por id: " + alunoDAO.getById(2));

        // adiciona
        //alunoDAO.create(new Aluno("Rose", 29, "RS"));

        // exclui
        //alunoDAO.delete(5);

        // atualiza
        //alunoDAO.update(6, new Aluno("Rose", 19, "RS"));

    }
}
