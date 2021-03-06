package org.codehaus.groovy.grails.web.binding

import org.codehaus.groovy.grails.web.servlet.mvc.AbstractGrailsControllerTests
import org.junit.Ignore

/**
 * @author Graeme Rocher
 * @since 1.1
 */
@Ignore
class BindToPropertyThatIsNotReadableTests extends AbstractGrailsControllerTests {

    protected void onSetUp() {
        gcl.parseClass '''
import grails.persistence.*

@Entity
class Book {

    String title

    private List calculateField

    static transients = ['calculatedField']

    void setCalculatedField(List value) {
        this.calculateField = value
    }

    int sum() { calculateField.sum() }
}
'''
    }

    void testBindToPropertyThatIsNotReadable() {
        def Book = ga.getDomainClass("Book").clazz
        def b = Book.newInstance()

        b.properties = [calculatedField:[1,2,3], title:"blah"]

        assertEquals 6, b.sum()
    }
}