# Triptogether
App android para divisão de gastos em viagem, utilizando sqlite para armazenar localmente as despesas.  


# Diretórios
## /src/main
Basicamente todo o código base da aplicação
### /res
Pasta de resources, aqui estão os layouts da UI, valores de Strings pt_br e en_us(falta implementar), estilos como cores e elementos gráficos.
### /java/com/teca/dudu/triptogether
Código de comportamentos do app.
#### activity
Código de funcionamento de cada estádo da aplicação.
#### adapter
Implementação do padrão de projeto Adapter para os componentes necessários.
#### dao
Implementação do padrão de projeto Data Access Object para os objetos do banco de dados.
#### layout
Implementação dos "fragments", são como várias activitys em uma activity, que aparecem na tela principal.
#### model
Modelo de estrutura dos dados do banco de dados.
#### teste
Códigos de teste, não adicionam nenhuma funcionalidade
#### util
Códigos ou trechos de códigos que auxiliam o desenvolvimento e não deveriam estar em nenhuma classe supracitada específica. Ex:RoundImage.java (Transforma uma imagem quadrada em um cirulo)

# Criadores
Este projeto foi criado na disciplina de Láboratório de Projeto e Sistemas e foi o TCC do respectivo grupo:  
Eduardo Henrique  
Tales Lopes  
Camila Magalães
