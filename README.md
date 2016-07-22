# refpath-count

Proof-of-concept technique to count distinct referral paths to conversions, implemented in Cascalog.

This (purportedly) orders events by a timestamp, groups events by a user (by `uid`), then creates a "refpath" of referrers (by `ref`), aggregating them into a string. These `uid refpath` groups are then grouped by `refpath` and counted, resulting in a count of referral paths.

## Does it work?

Hell if I know.

## Usage

Get familiar with the [Cascalog](https://github.com/nathanmarz/cascalog) and [Leiningen](https://github.com/technomancy/leiningen).

Once you've installed both, 

```
lein run
```

## License

Copyright © 2016 HYFN

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
