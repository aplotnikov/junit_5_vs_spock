import io.github.aplotnikov.junit5_vs_spock.annotations.Failed
//import io.github.aplotnikov.junit5_vs_spock.annotations.Slow

runner {
//    Exclude and include tests by annotations
//    include Fast
    exclude {
//        annotation Slow
        annotation Failed
    }

//    Collect static about launched failed test previous time first
//    optimizeRunOrder true
}