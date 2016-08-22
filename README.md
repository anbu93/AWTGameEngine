# AWTGameEngine
**Descriptions**: Java AWT GameDev
**About**: this is simple framework for game development in Java using abstract window toolkit (AWT).
**Example**:
1. create maven project ('mvn generate:archetype')
2. add to _pom.xml_ dependency:
    <dependency>
        <groupId>com.vova_cons</groupId>
        <artifactId>game2D_engine</artifactId>
        <version>1.0.9</version>
     </dependency>
3. package the our project ('mvn package')
4. open your project and use it.

**Example project**: see in my github project 'chess'

# Russuan tutorial:
**Создание простой игры:**
1. создаем файл core.xml который должен иметь вид

    <core>
        <window title="Заголовок окна" width="800" height="800"/>
        <main_loop tick_time="50"/>
        <init scene="initScene"/> <!-- сцена с которой будет начата игра -->
        <module path="module1/scenes.xml"/> <!-- а здесь указываем модули, их может быть несколько. И они могут лежать где угодно, главное указать где лежит файл конфигурации для модуля -->
        <module path="module2/scenes.xml"/> <!-- вот например второй модуль-->
    </core>

2. Создаем в своем проекте класс App.java и в нем метод 
    static void main(String[] args){
        CoreLoader.loadFrom("core.xml"); //даем загрузчику ядра наш файл конфигурации
        Core.core.startMainLoop(); // и запускаем главный цикл
        //все просто.
    }

3. Далее идем к нашему файлу module1/scenes.xml (к объявлению модуля)
    <module id="main"> //идентификатор модуля
        <resources src="img/source.xml"/> //файл конфигов для хранения ресурсов (только для этого модуля, каждый модуль имеет свои файлы ресурсов, отделеные друг от друга)
        <scene id="init" //а вот и сцена, определяем его идентификатор
               class="com.vova_cons.chess.BattleScene" //определяем его класс (он должен быть наследником от Scene)
               arg=""/> //а тут аргументы для постройки сцены (иногда требуется передавать аргументы (он будет вызван в момент развертывания модуля (то есть можно использовать один класс с различными аргументами и получать разные поведения)))
    </module>
4. Определим теперь файл ресурсов: 'img/source.xml'
    <source path="img/"> //путь к папке с ресурсами (не забываем что он может быть разным для разных модулей)
        <atlas src="bg.xml"/> //первый атлас
        <atlas src="figures.xml"/> //второй атлас
    </source>

5. Определяем атлас (например bg.xml)
    <atlas src="bg.png"> //указываем файл атласа (спрайтмапа)
        <sprite id="white_cell" x="0" y="0" w="100" h="100"/> //определяем отдельные спрайты (айди, левая верхняя точка по x и y, и ширину с высотой)
        <sprite id="black_cell" x="100" y="0" w="100" h="100"/>
        <sprite id="setted_cell" x="200" y="0" w="100" h="100"/>
    </atlas>

6. И в конце напишем класс простой сцены
    public class BattleScene extends Scene {
        private BattleSceneController controller;

        @Override
        public void construct(String args) { //вот конструктор для сцены (он разворачивается при активации модуля в котором он лежит) вот тут и нужны наши аргументы
            Resource.resource = new Resource(getModule());
            this.controller = new BattleSceneController(this);
        }

        @Override
        protected void concreteSceneAttach() {
            // вызывается при переходе к текущей сцене
        }

        @Override
        protected void concreteSceneDetach() {
            // перешли на другую сцену
        }

        @Override
        protected Controller getWindowController() {
            return new NullController(); //имплементирует Controller интерфейс (через Controller выполняется все управление)
        }

        @Override
        public void update(double deltaTimeInSecond) { //ну и обновление экрана
            Window window = getWindow();
            Sprite sprite = Resources.getSprite("cell");
            window.render(sprite, new Point(0,0));
        }

