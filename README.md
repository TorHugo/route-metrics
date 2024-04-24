# 🚗 Route Metrics

### A Route Metrics, é uma ferramenta versátil e poderosa que permite aos usuários registrar e analisar suas viagens com facilidade.
Desenvolvida com o objetivo de oferecer uma experiência intuitiva e informativa, essa aplicação oferece uma série de funcionalidades úteis para quem está em movimento!

### 📌 Algumas de nossas funcionalidades:
- `Registro de Rotas Flexível`: Os usuários podem iniciar uma rota a qualquer momento, sem a necessidade de definir um destino final. Isso proporciona liberdade para explorar e registrar viagens de forma espontânea.
- `Análise Detalhada da Viagem`: Com base na latitude e longitude do usuário, a aplicação calcula uma variedade de métricas importantes, incluindo velocidade máxima e mínima atingida durante o trajeto, distância percorrida, tempo decorrido e velocidade atual. Essas informações são apresentadas de forma clara e acessível para análise posterior.
- `Acesso Fácil aos Dados da Rota`: Ao final de cada viagem, todos os dados capturados estão disponíveis para acesso imediato. Isso permite que os usuários revisitem suas viagens e explorem os detalhes da sua jornada sempre que desejarem.
- `Estimativas de Percursos`: Em desenvolvimento, a funcionalidade de estimativas de percursos oferecerá aos usuários uma estimativa do consumo de combustível com base no cadastro do veículo. Isso permitirá uma melhor compreensão dos custos associados a viagens planejadas e ajudará os usuários a tomarem decisões informadas sobre suas jornadas.

### 💻 API
A aplicação foi desenvolvida utilizando DDD (Domain-Driven Design) como sua principal arquitetura. Enfatizo o uso dessa arquitetura pelos benefícios que ela tráz consigo, como: `testabilidade`, `manutenabilidade` e `escalabilidade`!
![Domain-Driven Design](/assets/driven-domain-design.png)

### 📚 Stack
A Route-Metrics API, foi desenvolvida utilizando:
- `Backend`: Java(`v17`); SpringBoot(`v3.2.4`);
- `Database`: PostgreSQL;
- `Mensageria`: RabbitMq;
- `DevOps`: CI/CD com GitHub Actions; Docker;
- `Cloud`: Oracle Cloud

Obs.:
- Como dito anteriormente essa aplicação foi desenvolvida na versão mais alta do SpringBoot(_03/2024_). Ou seja, toda a parte de autenticação e autorização foi desenvolvida utilizando SpringSecurity `v6.2.4`. E, utilizando boas práticas de autenticação, como `Secure` e `Http Only`.
- O `CI/CD` foi criado utilizando `GitHub Actions`, para automatizar o deploy da aplicação. Ou seja, ele faz o build do projeto, isso inclui a execução dos `testes unitários` e de integração para garantir a consistência do sistema; criação da imagem do projeto utilizando `Docker`, e deploy da mesma no `DockerHub`. Ao acessar a `VPS` externa, executa um script para realizar o pull da imagem, e a reinicialização do `docker-compose`.
- Para o bom funcionamento desta aplicação, foi desenvolvido um serviço (`worker`) para envios de e-mail (`@gmail`).

### 🚧 Features
Como citado anteriormente, a API está em processo de desenvolvimento. Aqui estão algumas features que ainda serão lançada futuramente:

- `Cadastro de Alerta`: A ideia é que o nosso usuário consiga criar alerta personalizados para critérios da viagem dele. E quando algum dos critérios estabelecidos for atingido, o sistema enviaria uma notificação ou um alerta de fato para chamar a atenção do usuário! Por exemplo:
  - Velocidade máxima;
  - Distancia percorrida;
  - Tempo percorrido;
  - Entre outros.
- `Cadastro de Veiculo`: O usuário poderá cadastrar seu veículo e com base em uma API externa o usuário teria como saber o consumo médio por km/h, para que assim, consiga se planejar para uma viagem.
- `Simulação de Viagem`: Com base na feature anterior, após o usuário ter cadastrado o veículo dele, seria possível ele fazer uma simulação para obter estatísticas úteis para ele, com por exemplo: 
  - Combustível gasto na viagem;
  - Distancia;
  - Entre outros.

Essas seriam apenas algumas features que poderão ser implementadas para o nosso usuário futuramente. Claro, podendo sofrer alterações, inclusões ou até mesmo exclusões.

### 👨‍🚀 Team Members
Developed/ Deployed by `Arruda, Victor Hugo`!

### 📨 Contacts
- [LinkedIn](https://www.linkedin.com/in/victorhugodev/)
- [Email](mailto:contato.arrudavictor@gmail.com)
- [Beacons](https://beacons.ai/tor_hugo)