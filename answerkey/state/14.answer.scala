def set[S](s: S): State[S, Unit] = State(_ => ((), s))