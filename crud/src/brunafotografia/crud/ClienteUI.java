package brunafotografia.crud;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ClienteUI extends JPanel {
    private ClienteManager clienteManager;

    public ClienteUI(ClienteManager clienteManager) {
        this.clienteManager = clienteManager;

        setLayout(new BorderLayout());

        JButton btnCadastrar = new JButton("Cadastrar Cliente");
        JButton btnLer = new JButton("Listar Clientes");
        JButton btnAtualizar = new JButton("Atualizar Cliente");
        JButton btnExcluir = new JButton("Excluir Cliente");

        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarCliente();
            }
        });

        btnLer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarClientes();
            }
        });

        btnAtualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarCliente();
            }
        });

        btnExcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirCliente();
            }
        });

        JPanel btnPanel = new JPanel(new FlowLayout());
        btnPanel.add(btnCadastrar);
        btnPanel.add(btnLer);
        btnPanel.add(btnAtualizar);
        btnPanel.add(btnExcluir);

        add(btnPanel, BorderLayout.CENTER);
    }

    private void cadastrarCliente() {
        int id = Integer.parseInt(JOptionPane.showInputDialog(this, "Digite o id do cliente:"));
        String nome = JOptionPane.showInputDialog(this, "Digite o nome do cliente:");
        String email = JOptionPane.showInputDialog(this, "Digite o email do cliente:");
        String telefone = JOptionPane.showInputDialog(this, "Digite o telefone do cliente:");

        Cliente cliente = new Cliente(id, nome, email, telefone);
        clienteManager.cadastrarCliente(cliente);
    }

    private void listarClientes() {
        List<Cliente> listaClientes = clienteManager.lerClientes();
        StringBuilder message = new StringBuilder("Lista de Clientes:\n");

        for (Cliente cliente : listaClientes) {
            message.append(cliente).append("\n");
        }

        JOptionPane.showMessageDialog(this, message.toString());
    }

    private void atualizarCliente() {
        int id = Integer.parseInt(JOptionPane.showInputDialog(this, "Digite o ID do cliente a ser atualizado:"));
        try {
            Cliente clienteExistente = clienteManager.obterClientePorId(id);

            if (clienteExistente != null) {
                String nome = JOptionPane.showInputDialog(this, "Digite o novo nome do cliente:", clienteExistente.getNome());
                String email = JOptionPane.showInputDialog(this, "Digite o novo email do cliente:", clienteExistente.getEmail());
                String telefone = JOptionPane.showInputDialog(this, "Digite o novo telefone do cliente:", clienteExistente.getTelefone());

                Cliente clienteAtualizado = new Cliente(id, nome, email, telefone);

                boolean sucesso = clienteManager.atualizarCliente(clienteAtualizado);

                if (sucesso) {
                    JOptionPane.showMessageDialog(this, "Cliente atualizado com sucesso.");
                } else {
                    JOptionPane.showMessageDialog(this, "Erro ao atualizar cliente.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Cliente não encontrado.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID inválido. Digite um número válido.");
        }
    }


    private void excluirCliente() {
        int id = Integer.parseInt(JOptionPane.showInputDialog(this, "Digite o ID do cliente a ser excluído:"));
        try {
            boolean sucesso = clienteManager.excluirCliente(id);

            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Cliente excluído com sucesso.");
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao excluir cliente.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "ID inválido. Digite um número válido.");
        }
    }
}