import java.util.*

fun main() {

    val words =
//        listOf<String>(
//        "wrt",
//        "wrf",
//        "er",
//        "ett",
//        "rftt",
//    )
//        listOf<String>(
//            "baa", "abcd", "abca", "cab", "cad"
//        )

        listOf<String>(
            "caa", "aaa", "aab"
        )

    println(findWeirdLexicography(words).toString())

}

fun findWeirdLexicography(words: List<String>): List<Char> {
    val adjs: Map<Char, List<Char>> = createAdjs(words)
    println(adjs)
    return topologicalSort(adjs)
}

val vis = mutableSetOf<Char>()
val path = mutableSetOf<Char>()
val stk = Stack<Char>()
fun topologicalSort(adjs: Map<Char, List<Char>>): List<Char> {
    for (k in adjs.keys) {
        dfs(k, adjs)
    }
    val res = mutableListOf<Char>()
    while (stk.isNotEmpty()) {
        res.add(stk.pop())
    }
    return res
}

fun dfs(k: Char, adjs: Map<Char, List<Char>>) {
    if(k in vis ) return

    vis.add(k)
    adjs[k]?.let {
        for (nbr in it) {
            dfs(nbr, adjs)
        }
    }
    stk.push(k)
}

fun createAdjs(words: List<String>): Map<Char, List<Char>> {
    val adjs = mutableMapOf<Char, List<Char>>()
    for (i in 0..words.size-2) {
        val s1 = words[i]
        val s2 = words[i+1]
        for(j in s1.indices){
           if(s1[j] != s2[j]) {
               adjs[s1[j]] = adjs.getOrPut(s1[j]) { mutableListOf() } + s2[j]
               break
           }
        }
    }
    return adjs
}
