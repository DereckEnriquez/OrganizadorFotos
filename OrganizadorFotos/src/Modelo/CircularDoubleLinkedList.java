package Modelo;

import java.util.Iterator;
import java.util.ListIterator;

/**
 *
 * @author G6
 */
public class CircularDoubleLinkedList<E> implements List<E> {
    private String nombreAlbum;
    private String descripcion;
    private Node<E> last;
    private int tam;

    public Iterator<E> iterator() {
        Iterator<E> iter = new Iterator<E>() {
            Node<E> node = last.sig;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public E next() {
                E temp = node.datos;
                node = node.sig;
                return temp;
            }

        };
        return iter;
    }

    public ListIterator<E> listIterator() {
        return listIterator(0);
    }

    public ListIterator<E> listIterator(int index) {
        if (index < 0 || index >= tam) {
            throw new IndexOutOfBoundsException("Indice fuera del limite");
        }

        ListIterator<E> list = new ListIterator<E>() {
            private int i = index;
            private Node<E> node = getNode(i);

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public E next() {
                E tmp = node.datos;
                node = node.sig;
                if (i == tam - 1) {
                    i = 0;
                } else {
                    i++;
                }
                return tmp;
            }

            @Override
            public boolean hasPrevious() {
                return node != null;
            }

            @Override
            public E previous() {
                E tmp = node.datos;
                node = node.ant;
                if (i == 0) {
                    i = tam - 1;
                } else {
                    i--;
                }
                return tmp;
            }

            @Override
            public int nextIndex() {
                if (!isEmpty()) {
                    if (i == tam - 1) {
                        return i = 0;
                    } else {
                        return i + 1;
                    }
                }
                return -1;
            }

            @Override
            public int previousIndex() {
                if (hasPrevious()) {
                    if (i == 0) {
                        return i = tam - 1;
                    } else {
                        return i - 1;
                    }
                }
                return -1;
            }

            @Override
            public void remove() {
                if (i == 0) {
                    removeFirst();
                } else if (i == tam - 1) {
                    removeLast();
                } else {
                    Node<E> node = getNode(i - 1);
                    Node<E> nRemover = node.sig;
                    Node<E> siguiente = nRemover.sig;
                    nRemover.datos = null;
                    nRemover.sig = nRemover.ant = null;
                    node.sig = siguiente;
                    siguiente.ant = node;
                    tam--;
                }
            }

            @Override
            public void set(E arg0) {
                if (arg0 == null) {
                    throw new NullPointerException();
                } else {
                    Node<E> node = getNode(i);
                    node.datos = arg0;
                }
            }

            @Override
            public void add(E arg0) {
                insert(i, arg0);
            }
        };
        return list;
    }

    private class Node<E> {

        private E datos;
        private Node<E> sig;
        private Node<E> ant;

        public Node(E datos) {
            this.datos = datos;
            this.sig = null;
            this.ant = null;
        }

    }

    public CircularDoubleLinkedList() {
        last = null;
        tam = 0;
    }

    public CircularDoubleLinkedList(String nombreAlbum) {
        this.nombreAlbum = nombreAlbum;
        last = null;
        tam = this.tam;
    }

    public CircularDoubleLinkedList(String nombreAlbum, String descripcion) {
        this.nombreAlbum = nombreAlbum;
        this.descripcion = descripcion;
        tam = this.tam;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombreAlbum() {
        return nombreAlbum;
    }

    public void setNombreAlbum(String nombreAlbum) {
        this.nombreAlbum = nombreAlbum;
    }

    @Override
    public boolean addFirst(E e) {
        if (e == null) {
            return false;
        }
        Node<E> node = new Node(e);
        if (isEmpty()) {
            last = node;
            last.sig = node;
        } else {
            Node<E> second = last.sig;
            last.sig = node;
            node.sig = second;
            second.ant = node;
            node.ant = last;
        }
        tam++;
        return true;
    }

    @Override
    public boolean addLast(E e) {
        if (e == null) {
            return false;
        }
        Node<E> node = new Node(e);
        if (isEmpty()) {
            last = last.sig = node;
        } else {
            node.ant = last;
            Node<E> node1 = last.sig;
            node.sig = node1;
            node1.ant = node;
            last.sig = node;
            last = node;
        }
        tam++;
        return true;
    }

    @Override
    public E getFirst() {
        if (isEmpty()) {
            throw new IllegalStateException("La lista que selecciono esta vacía");
        }
        return last.sig.datos;
    }

    @Override
    public E getLast() {
        if (isEmpty()) {
            throw new IllegalStateException("La lista está vacía");
        }
        return last.datos;
    }

    @Override
    public int indexOf(E e) {
        if (e == null) {
            return -1;
        }
        if (isEmpty()) {
            return -1;
        }
        Node<E> node = last.sig;
        for (int i = 0; i < tam; i++) {
            if (node.datos.equals(e)) {
                return i;
            }
            node = node.sig;
        }
        return -1;
    }

    @Override
    public int size() {
        return tam;
    }

    @Override
    public boolean removeLast() {
        if (isEmpty()) {
            return false;
        }
        last.datos = null;
        if (tam == 1) {
            last = null;
        } else {
            Node<E> node = last.ant;
            Node<E> node1 = last.sig;
            node.sig = node1;
            node1.ant = node;
            last.sig = last.ant = null;
            last = node;
        }
        tam--;
        return true;
    }

    @Override
    public boolean removeFirst() {
        if (isEmpty()) {
            return false;
        }
        if (tam == 1) {
            last.datos = null;
            last = last.sig = last.ant = null;
        } else {
            Node<E> eliminar = last.sig;
            Node<E> newNode = eliminar.sig;
            last.sig = newNode;
            newNode.ant = last;
            eliminar.sig = eliminar.ant = null;
            eliminar.datos = null;

        }
        tam--;
        return true;
    }

    public Node<E> getNode(int index) {
        if (index == 0) {
            return last.sig;
        }
        if (index == tam - 1) {
            return last;
        }
        Node<E> node;
        if (index < tam / 2) {
            node = last.sig;
            for (int i = 0; i < index; i++) {
                node = node.sig;
            }
        } else {
            node = last;
            for (int i = tam - 1; i > index; i--) {
                node = node.ant;
            }
        }
        return node;
    }

    @Override
    public boolean insert(int index, E e) {
        if (index > tam - 1 || index < 0) {
            throw new IndexOutOfBoundsException("El indice pedido supera el tamaño de la lista.");
        }
        if (e == null || isEmpty()) {
            return false;
        }
        if (index == 0) {
            return addFirst(e);
        }
        Node<E> node = new Node<>(e);
        Node<E> n = getNode(index);
        Node<E> nodeAnt = n.ant;
        nodeAnt.sig = n.ant = node;
        node.ant = nodeAnt;
        node.sig = n;
        tam++;
        return true;
    }

    @Override
    public boolean set(int index, E e) {
        if (index > tam - 1 || index < 0) {
            throw new IndexOutOfBoundsException("El indice pedido supera el tamaño de la lista.");
        }
        if (e == null) {
            return false;
        }
        if (index == 0) {
            last.sig.datos = e;
        }
        if (index == tam - 1) {
            last.datos = e;
        } else {
            Node<E> node = getNode(index);
            node.datos = e;
        }
        return true;
    }

    @Override
    public boolean isEmpty() {
        return tam == 0;
    }

    @Override
    public E get(int index) {
        if (isEmpty()) {
            throw new IllegalStateException("Lista vacia");
        }
        if (index > tam - 1 || index < 0) {
            throw new IndexOutOfBoundsException("El indice pedido supera el tamaño de la lista.");
        }
        if (index == 0) {
            return last.sig.datos;
        }
        if (index == tam - 1) {
            return last.datos;
        }
        Node<E> node = getNode(index);
        return node.datos;
    }

    @Override
    public boolean contains(E e) {
        Node node = last.sig;
        do {
            if (node.datos.equals(e)) {
                return true;
            }
            node = node.sig;
        } while (node != last);

        return false;
    }

    @Override
    public boolean remove(int index) {
        if (index >= tam) {
            throw new IndexOutOfBoundsException("Indice fuera de los límites");
        }
        if (index == 0) {
            return removeFirst();
        }
        if (index == tam - 1) {
            return removeLast();
        }
        Node<E> node = getNode(index - 1);
        Node<E> nRemover = node.sig;
        Node<E> nSig = nRemover.sig;
        nRemover.datos = null;
        node.sig = nSig;
        nSig.ant = node;
        tam--;
        return true;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<E> node = last.sig;
        for (int i = 0; i < tam - 1; i++) {
            sb.append(node.datos);
            sb.append(",");
            node = node.sig;

        }
        sb.append(last.datos);
        sb.append("]");
        return sb.toString();
    }

}
