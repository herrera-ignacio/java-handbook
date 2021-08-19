# 67: Optimize judiciously

* Aphorisms
* Overview

## Aphorisms

> "More computing sins are committed in the name of efficiency (without necessarily achieving it) than for any other single reason, including blind stupidity." - William A. Wulf

> "We *should* forget about small efficiencies, say about 97% of the time: premature optimization is the root of all evil." - Donald E. Knuth

> "We follow two rules in the matter of optimization:
>   Rule 1. Don't do it.
>   Rule 2 (for experts only). Don't do it yet, that is, not until you have a perfectly clear and unoptimized solution." - M. A. Jackson

> "Common wisdom says that programs spend 90 percent of their time in 10 percent of their code."

## Overview

* It's easy to do more harm than good, especially if you optimize prematurely.

* Don't sacrifice soudn architectural principles for performance. **Strive to write good programs rather than fast ones**. If a good program is not fast enough, its architecture will allow it to be optimized. Implementation problems can be fixed by later optimization, but pervasive architectural flaws that limit performance can be impossible to fix without rewriting the system. Therefore you still need to think about performance during the design process.

* **Strive to avoid design decisions that limit performance**. Be careful with design components asuch as wire-level protocols, persistent data formats, and APIs specifying interactions between components and with the outside world. Not only are these design components difficult to change after the fact, but all of them can place significant limitations on the performance that a system can ever achieve.

* Consider the performance consequences of your API design decisions.
  * It is a very bad idea to warp an API to achieve good performance.

* **Measure performance before and after each attempted optimization**. Profiling tools can help you decide where to focus your optimization efforts.
